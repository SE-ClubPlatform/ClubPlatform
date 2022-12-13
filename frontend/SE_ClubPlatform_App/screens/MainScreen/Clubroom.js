/* eslint-disable react/self-closing-comp */
/* eslint-disable react-native/no-inline-styles */
import React, {useState} from 'react';
import {
  View,
  Text,
  Button,
  TouchableOpacity,
  Dimensions,
  Image,
  StyleSheet,
} from 'react-native';
import {ScrollView} from 'react-native-gesture-handler';
import {set} from 'react-native-reanimated';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Clubroom({navigation}) {
  const [userCount, setUserCount] = useState(5);
  const [out, setOut] = useState(true);

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'white',
      }}>
      <Topbar navigation={navigation} />
      <View style={{padding: Width * 0.05}}>
        <Text style={styles.fontStyle}>동아리방 사용현황</Text>
        <View style={{alignItems: 'center', marginBottom: Height * 0.03}}>
          <View style={styles.countArea}>
            <Text style={{}}>현재 입장 인원</Text>
            <Text>{userCount}명</Text>
          </View>
          <View style={{flexDirection: 'row'}}>
            <TouchableOpacity
              style={{
                ...styles.exitButton,
                backgroundColor: out ? '#4d53c1' : '#F3F3F3',
              }}
              onPress={() => (out === false ? setOut(!out) : null)}
              activeOpacity={0.85}>
              <Image
                style={styles.exitIcon}
                source={
                  out
                    ? require('../../icons/in.png')
                    : require('../../icons/in_grey.png')
                }
              />
              <Text style={{color: out ? 'white' : '#979797'}}>입장하기</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={{
                ...styles.exitButton,
                backgroundColor: out ? '#F3F3F3' : '#4d53c1',
              }}
              onPress={() => (out === true ? setOut(!out) : null)}
              activeOpacity={0.85}>
              <Image
                style={styles.exitIcon}
                source={
                  out
                    ? require('../../icons/out_grey.png')
                    : require('../../icons/out.png')
                }
              />
              <Text style={{color: out ? '#979797' : 'white'}}>퇴장하기</Text>
            </TouchableOpacity>
          </View>
        </View>
        <View>
          <Text
            style={{
              fontSize: 20,
              marginBottom: Height * 0.02,
              color: 'black',
            }}>
            동아리방 사용 기록
          </Text>
          <ScrollView
            style={{
              height: Height * 0.35,
              borderWidth: 0.1,
              borderRadius: 5,
            }}></ScrollView>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
    color: 'black',
  },
  countArea: {
    height: Height * 0.15,
    width: Width * 0.5,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 10,
    backgroundColor: '#F5F5F5',
    marginBottom: Height * 0.03,
  },
  exitButton: {
    height: Width * 0.3,
    width: Width * 0.3,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 50,
    elevation: 2,
    marginHorizontal: Width * 0.04,
    backgroundColor: '#4d53c1',
  },
  exitIcon: {
    width: Width * 0.09,
    height: Width * 0.09,
    resizeMode: 'stretch',
    marginBottom: Height * 0.005,
    marginRight: Width * 0.025,
  },
});

export default Clubroom;
