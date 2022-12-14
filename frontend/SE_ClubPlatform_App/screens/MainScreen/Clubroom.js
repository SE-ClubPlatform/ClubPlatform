/* eslint-disable react/self-closing-comp */
/* eslint-disable react-native/no-inline-styles */
import React, {useEffect, useState} from 'react';
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
import userToken from '../../recoils/userToken';
import {useRecoilState, useRecoilValue} from 'recoil';
import axios from 'axios';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Clubroom({navigation}) {
  const [userCount, setUserCount] = useState(5);
  const [out, setOut] = useState(true);
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [clublog, setClubLog] = useState();

  async function getData(token, clubId) {
    try {
      console.log(token);
      console.log(clubId);
      const response = await axios.get(
        `http://sogong-group3.kro.kr/clubRoom/${clubId}`,
        {
          headers: {
            Authorization: token,
          },
        },
      );
      // setClubInfo(response.data);
      console.log(response.data.data);
      setClubLog(response.data.data);
      console.log(clublog);
    } catch (e) {
      // alert('아이디와 비밀번호를 다시 확인해주세요 .');
      // setLoading(false);
      console.log(e);
    }
  }

  async function putData(token, clubId) {
    try {
      console.log(token);
      console.log(clubId);
      const response = await axios.put(
        `http://sogong-group3.kro.kr/clubRoom/${clubId}`,
        {
          body: {
            Authorization: token,
          },
        },
      );
      if (response.data) {
        console.log(response);
      }
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    getData(`Bearer ${userToken_R}`, 1);
  }, []);

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
            <Text>{clublog ? clublog.length : 0}명</Text>
          </View>
          <View style={{flexDirection: 'row'}}>
            <TouchableOpacity
              style={{
                ...styles.exitButton,
                backgroundColor: out ? '#4d53c1' : '#F3F3F3',
              }}
              onPress={() => {
                out === false ? setOut(!out) : null;
                putData(`Bearer ${userToken_R}`, 1);
              }}
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
              onPress={() => {
                out === true ? setOut(!out) : null;
                putData(`Bearer ${userToken_R}`, 1);
              }}
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
              height: Height * 0.3,
              borderWidth: 0.1,
              borderRadius: 5,
            }}>
            <View
              style={{
                flexDirection: 'row',
                justifyContent: 'flex-end',
                paddingVertical: Height * 0.02,
                paddingHorizontal: Width * 0.04,
              }}>
              <Text
                style={{
                  fontSize: 16,
                  fontWeight: 'bold',
                  marginRight: Width * 0.215,
                }}>
                일시
              </Text>
              <Text
                style={{
                  fontSize: 16,
                  fontWeight: 'bold',
                  marginRight: Width * 0.09,
                }}>
                학번
              </Text>
              <Text
                style={{
                  fontSize: 16,
                  fontWeight: 'bold',
                  marginRight: Width * 0.06,
                }}>
                이름
              </Text>
              <Text
                style={{
                  fontSize: 16,
                  fontWeight: 'bold',
                }}>
                상태
              </Text>
            </View>
            <View style={{paddingHorizontal: Width * 0.04}}>
              {clublog
                ? clublog.map(log => (
                    <View
                      key={log.time}
                      style={{
                        flexDirection: 'row',
                        justifyContent: 'flex-end',
                        marginBottom: Height * 0.002,
                      }}>
                      <Text style={{marginRight: Width * 0.02}}>
                        {log.clubRoomId}
                      </Text>
                      <Text style={{marginRight: Width * 0.025}}>
                        {log.time}
                      </Text>
                      <Text style={{marginRight: Width * 0.025}}>
                        {log.studentId}
                      </Text>
                      <Text style={{marginRight: Width * 0.05}}>
                        {log.name}
                      </Text>
                      <Text
                        style={{
                          marginRight: Width * 0.002,
                          color: log.type === '입장' ? 'blue' : 'red',
                        }}>
                        {log.type}
                      </Text>
                    </View>
                  ))
                : null}
            </View>
          </ScrollView>
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
