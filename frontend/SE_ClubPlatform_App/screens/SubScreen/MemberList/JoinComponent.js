import React, {useState, useRef, useEffect} from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  Dimensions,
} from 'react-native';
import axios, {AxiosHeaders} from 'axios';
import {useRecoilState} from 'recoil';
import userToken from '../../../recoils/userToken';
import {ScrollView} from 'react-native-gesture-handler';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function JoinComponent(applyData) {
  const [selectedNumber, setUser] = useState(0);
  const [isVisible, setUnVisible] = useState(true);
  const infoData = applyData && applyData.applyData;

  const selectMember = key => {
    setUser(selectedNumber => (selectedNumber = key));
    setUnVisible(prev => !prev);
  };

  useEffect(() => {}, []);
  return (
    <View>
      {infoData &&
        infoData.map(data => (
          <View key={data.studentId} style={{marginBottom: 10}}>
            {selectedNumber !== data.studentId && (
              <View style={styles.gray_card}>
                <View>
                  <View style={{flexDirection: 'row'}}>
                    <View style={styles.text_box}>
                      <View style={{flexDirection: 'row'}}>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_attribute_text}>이름</Text>
                        </View>
                        <View style={styles.apply_text_container}>
                          <Text
                            style={[
                              styles.apply_context_text,
                              {marginRight: 20},
                            ]}>
                            {data.name}
                          </Text>
                        </View>
                      </View>
                    </View>
                    <View style={styles.text_box}>
                      <View style={{flexDirection: 'row'}}>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_attribute_text}>학번</Text>
                        </View>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_context_text}>
                            {data.studentId}
                          </Text>
                        </View>
                      </View>
                    </View>
                  </View>
                  <View style={{flexDirection: 'column'}}>
                    <View style={styles.text_box}>
                      <View style={{flexDirection: 'row'}}>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_attribute_text}>학과</Text>
                        </View>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_context_text}>
                            {data.major}
                          </Text>
                        </View>
                      </View>
                    </View>
                  </View>
                  <View style={{flexDirection: 'row'}}>
                    <View style={styles.text_box}>
                      <View style={{flexDirection: 'row'}}>
                        <View style={styles.apply_text_container}>
                          <Text style={styles.apply_attribute_text}>
                            연락처
                          </Text>
                        </View>
                        <View
                          style={[
                            styles.apply_text_container,
                            {marginRight: 20},
                          ]}>
                          <Text style={styles.apply_context_text}>
                            {data.phoneNumber}
                          </Text>
                        </View>
                      </View>
                      <View style={styles.apply_button_container}>
                        <TouchableOpacity
                          style={styles.apply_button}
                          onPress={() => selectMember(data.studentId)}>
                          <Text style={styles.apply_button_text}>삭제</Text>
                        </TouchableOpacity>
                      </View>
                      <View style={styles.apply_button_container}>
                        <TouchableOpacity
                          style={[
                            styles.apply_button,
                            {backgroundColor: '#d9d9d9'},
                          ]}
                          onPress={() => selectMember(data.studentId)}>
                          <Text style={styles.apply_button_text}>승인</Text>
                        </TouchableOpacity>
                      </View>
                    </View>
                  </View>
                </View>
              </View>
            )}
          </View>
        ))}
    </View>
  );
}

function JoinBlock(applyData) {
  return (
    <ScrollView style={styles.context_container}>
      <JoinComponent applyData={applyData && applyData.applyData} />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  context_container: {
    backgroundColor: '#ffffff',
    flex: 1,
    marginLeft: 15,
    marginRight: 15,
    marginBottom: 40,
    marginTop: 24,
    borderWidth: 1,
    padding: 10,
    borderRadius: 5,
  },
  gray_card: {
    height: Height * 0.1,
    backgroundColor: '#eeeeee',
    flex: 1,
    borderRadius: 7,
    marginLeft: 10,
    marginRight: 10,
    marginBottom: 5,
    //alignSelf: 'baseline',
    marginTop: 5,
    paddingLeft: 10,
    paddingTop: 8,
    justifyContent: 'space-between',
    alignItems: 'baseline',
  },
  text_box: {
    //borderWidth: 1,
    flexDirection: 'row',
    //width: 300,
  },
  apply_text_container: {
    paddingRight: 5,
    paddingBottom: 2,
    //borderWidth: 0.5,
    opacity: 1,
  },
  apply_attribute_text: {
    color: '#cccccc',
    opacity: 1,
  },
  apply_context_text: {
    color: '#4f4f4f',
    opacity: 1,
  },
  test: {
    borderWidth: 2,
  },
  apply_button_container: {
    height: Height * 0.03,
    width: Width * 0.15,
    //borderWidth: 1,
    marginHorizontal: 5,
  },
  apply_button: {
    borderRadius: 8,
    backgroundColor: '#E58E8E',
    opacity: 0.5,
    alignItems: 'center',
    justifyContent: 'center',
    //marginHorizontal: 10,
  },
  apply_button_text: {
    color: '#000000',
  },
});
export default JoinBlock;
