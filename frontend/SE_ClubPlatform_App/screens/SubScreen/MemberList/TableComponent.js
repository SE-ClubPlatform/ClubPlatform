/* eslint-disable react/self-closing-comp */
/* eslint-disable react-native/no-inline-styles */
import React, {useState, useEffect} from 'react';
import {
  Alert,
  View,
  Text,
  Button,
  TouchableOpacity,
  Dimensions,
  StyleSheet,
} from 'react-native';
import axios, {AxiosHeaders} from 'axios';
import {useRecoilState} from 'recoil';
import userToken from '../../../recoils/userToken';
import {ScrollView} from 'react-native-gesture-handler';

const heads = ['이름', '학번', '학과', '연락처'];
const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function IndexList() {
  return (
    <View style={styles.indexContainer}>
      <View style={(styles.indexComponent, {width: Width * 0.12})}></View>
      <View style={(styles.indexComponent, {width: Width * 0.17})}>
        <Text style={styles.indexText}>이름</Text>
      </View>
      <View style={(styles.indexComponent, {width: Width * 0.25})}>
        <Text style={styles.indexText}>학번</Text>
      </View>
      <View style={(styles.indexComponent, {marginRight: 0})}>
        <Text style={styles.indexText}>학과</Text>
      </View>
    </View>
  );
}
function BodyList(bodyData) {
  const [selectedNumber, setUser] = useState(0);
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [member, setManagerAuth] = useState();
  const [isVisible, setVisible] = useState(true);

  const infoData = bodyData && bodyData.bodyData;

  const selectMember = key => {
    setUser(selectedNumber => (selectedNumber = key));
  };

  async function getAuth(token, clubId, studentId) {
    console.log(
      'http://sogong-group3.kro.kr/club/' +
        clubId +
        '/members/' +
        studentId +
        '/changeAuthority',
    );
    try {
      const response = await axios.get(
        'http://sogong-group3.kro.kr/club/' +
          clubId +
          '/members/' +
          studentId +
          '/changeAuthority',
        {
          headers: {
            Authorization: token,
          },
        },
      );
      setManagerAuth(response.data);
    } catch (e) {
      console.log(e);
    }
  }
  async function deleteMember(token, clubId, studentId) {
    try {
      const response = await axios.delete(
        'http://sogong-group3.kro.kr/club/' + clubId + '/members/' + studentId,
        {
          headers: {
            Authorization: token,
          },
        },
      );
    } catch (e) {
      console.log(e);
    }
  }
  const alertGiveAuth = id => {
    Alert.alert('해당 회원에게 동아리 임원 권한을 주시겠습니까?', null, [
      {
        text: '아니요',
        onPress: () => null,
      },
      {text: '네', onPress: () => getAuth(userToken_R, 1, id)},
    ]);
  };

  const alertDeleteMember = id =>
    Alert.alert('해당 회원을 동아리에서 삭제하시겠습니까?', null, [
      {
        text: '아니요',
        onPress: () => null,
      },
      {text: '네', onPress: () => deleteMember(userToken_R, 1, id)},
    ]);

  useEffect(() => {});

  return (
    <View style={styles.bodyWrapper}>
      {infoData &&
        infoData.map(data => (
          <View key={data.studentId}>
            {selectedNumber === data.studentId ? (
              <TouchableOpacity onPress={() => selectMember(data.order)}>
                <View style={styles.selectedComponent}>
                  <View style={{flexDirection: 'row'}}>
                    <View style={(styles.bodyComponent, {width: Width * 0.12})}>
                      <Text style={styles.bodyText}>{data.order}</Text>
                    </View>
                    <View style={(styles.bodyComponent, {width: Width * 0.17})}>
                      <Text style={styles.bodyText}>{data.userName}</Text>
                    </View>
                    <View style={(styles.bodyComponent, {width: Width * 0.25})}>
                      <Text style={styles.bodyText}>{data.studentId}</Text>
                    </View>
                    <View style={(styles.bodyComponent, {marginRight: 0})}>
                      <Text style={styles.bodyText}>{data.major}</Text>
                    </View>
                  </View>
                  <View style={styles.phoneNumberContainer}>
                    <View style={{width: Width * 0.17}}>
                      <Text style={styles.indexText}>연락처</Text>
                    </View>
                    <Text style={styles.bodyText}>{data.phoneNumber}</Text>
                  </View>
                  <View style={styles.memberButtonContainer}>
                    <View style={styles.apply_button_container}>
                      <TouchableOpacity
                        style={[styles.apply_button, {width: Width * 0.3}]}
                        onPress={() => alertDeleteMember(data.studentId)}>
                        <Text style={styles.apply_button_text}>부원 삭제</Text>
                      </TouchableOpacity>
                    </View>
                    <View style={styles.apply_button_container}>
                      <TouchableOpacity
                        style={[
                          styles.apply_button,
                          {backgroundColor: '#d9d9d9'},
                        ]}
                        onPress={() => alertGiveAuth(data.studentId)}>
                        <Text style={styles.apply_button_text}>
                          동아리 임원 지정
                        </Text>
                      </TouchableOpacity>
                    </View>
                  </View>
                </View>
              </TouchableOpacity>
            ) : (
              <TouchableOpacity onPress={() => selectMember(data.studentId)}>
                <View
                  style={{
                    flexDirection: 'row',
                    marginBottom: 5,
                    marginLeft: 5,
                  }}>
                  <View style={(styles.bodyComponent, {width: Width * 0.12})}>
                    <Text style={styles.bodyText}>{data.order}</Text>
                  </View>
                  <View style={(styles.bodyComponent, {width: Width * 0.17})}>
                    <Text style={styles.bodyText}>{data.userName}</Text>
                  </View>
                  <View style={(styles.bodyComponent, {width: Width * 0.25})}>
                    <Text style={styles.bodyText}>{data.studentId}</Text>
                  </View>
                  <View style={(styles.bodyComponent, {marginRight: 0})}>
                    <Text style={styles.bodyText}>{data.major}</Text>
                  </View>
                </View>
              </TouchableOpacity>
            )}
          </View>
        ))}
    </View>
  );
}

function MemberListBlock(bodyData) {
  const infoData = bodyData && bodyData.bodyData;
  return (
    <ScrollView style={styles.context_container} stickyHeaderIndices={[0]}>
      <IndexList />
      <BodyList bodyData={infoData} />
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
    paddingRight: 10,
    paddingLeft: 10,
    borderRadius: 5,
    elevation: 3,
  },
  indexContainer: {
    paddingTop: 10,
    paddingBottom: 10,
    marginLeft: 5,
    flexDirection: 'row',
    backgroundColor: '#FFFFFF',
  },
  indexComponent: {
    marginRight: 10,
    width: Width * 0.19,
  },
  indexText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#4f4f4f',
  },
  bodyText: {
    fontSize: 16,
    color: '#4f4f4f',
  },
  bodyWrapper: {
    flexDirection: 'column',
    marginBottom: 10,
  },
  bodyComponent: {
    marginRight: 10,
    width: Width * 0.19,
  },
  selectedComponent: {
    marginTop: 5,
    paddingTop: 5,
    marginBottom: 10,
    paddingLeft: 5,

    borderRadius: 8,
    backgroundColor: '#EEEEEE',
    height: Height * 0.13,
  },
  phoneNumberContainer: {
    flexDirection: 'row',
    marginLeft: Width * 0.12,
    marginTop: 5,
  },
  memberButtonContainer: {
    flexDirection: 'row-reverse',
    marginTop: 10,
    marginLeft: 10,
  },
  apply_button_container: {
    height: Height * 0.035,
    width: Width * 0.3,
    marginHorizontal: 5,
  },
  apply_button: {
    borderRadius: 8,
    opacity: 0.5,
    backgroundColor: '#E58E8E',
    alignItems: 'center',
    justifyContent: 'center',
    height: Height * 0.035,

    //marginHorizontal: 10,
  },
  apply_button_text: {
    color: '#000000',
  },
});

export default MemberListBlock;
