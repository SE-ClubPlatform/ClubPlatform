/* eslint-disable react/self-closing-comp */
/* eslint-disable react-native/no-inline-styles */
import React, { useState } from 'react';
import {
  View,
  Text,
  Button,
  Dimensions,
  TouchableOpacity,
  StyleSheet,
  Image,
  ToastAndroid,
} from 'react-native';
import {TextInput} from 'react-native-gesture-handler';
import { useRecoilState } from 'recoil';
import userToken from '../../recoils/userToken';
import { RadioButton } from 'react-native-paper';
import Topbar from '../Bar/Topbar';
import axios from 'axios';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

const year = ['2022', '2023', '2024'];

function PostActivity({navigation}) {
  const [title, setTitle] = useState();
  const [introduce, setIntroduce] = useState();
  const [finishDate, setFinishDate] = useState();
  const [content, setContent]= useState();
  const [userToken_R, setUserToken_R] = useRecoilState(userToken);
  const [voteActivity, setVoteActivity] = useState();
  

  async function postWork(token, clubId, title, introduce, finishDate) {
    try {
      const response = await axios.post(
        "http://sogong-group3.kro.kr/club/" + clubId + "/work",
        {
          headers: {
            Authorization: token,
          },
          title,
          introduce,
          finishDate,
        },
      );
      console.log(response.data.state)
      if (response.data.state === 200) {
        console.log(response.data.message)
        navigation.replace('WorkFlow');
      } else {
        alert('내용을 확인해주세요');
      }
    } catch (e) {
      console.log(e);
    }
  }
  async function postWork(token, clubId, title, introduce, finishDate) {
    try {
      const response = await axios.post(
        "http://sogong-group3.kro.kr/club/" + clubId + "/work/" + workId + "/phase",
        {
          headers: {
            Authorization: token,
          },
          title,
          introduce,
          finishDate,
        },
      );
      console.log(response.data.state)
      if (response.data.state === 200) {
        console.log(response.data.message)
        navigation.replace('WorkFlow');
      } else {
        alert('내용을 확인해주세요');
      }
    } catch (e) {
      console.log(e);
    }
  }

  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View style={{flex: 1, margin: Width * 0.05}}>
        <View style={{flex: 0.1, justifyContent: 'center'}}>
          <Text style={{fontSize: 28}}>활동 생성</Text>
        </View>
        <View
          style={{
            flex: 0.8,
          }}>
          <View style={styles.textTopArea}>
            <TextInput
              style={{fontSize: 15}}
              placeholder="제목을 입력해주세요"
              onChangeText={(text) => setTitle(text)}
              autoCapitalize="none"></TextInput>
          </View>
          <View style={styles.textBottomArea}>
            <TextInput
              style={{
                height: Height * 0.43,
                fontSize: 15,
                flexShrink: 1,
                textAlignVertical: 'top',
              }}
              placeholder="내용을 입력해주세요"
              autoCapitalize="none"
              onChangeText={(text) => setIntroduce(text)}
              multiline={true}></TextInput>
            <View style={styles.voteContainer}>
              <TouchableOpacity style={[styles.checkBox]}>
                <Image
                  style={{
                    width: Width * 0.05,
                    height: Width * 0.05,
                    marginRight: Width * 0.015,
                  }}
                  source={require('../../icons/unchecked.png')}
                />
              </TouchableOpacity>
              <Text>투표</Text>
            </View>
            <View style={styles.horizontalLine}></View>
            <TextInput
              placeholder="마감날짜를 입력해주세요(0000-00-00)"
              onChangeText={(text) => setFinishDate(text)}
            />
          </View>
        </View>
        <View
          style={{
            flex: 0.1,
            justifyContent: 'center',
            alignItems: 'flex-end',
          }}>
          <TouchableOpacity 
          style={styles.registerButton}
          onPress={()=> postWork(userToken_R, 1, finishDate, introduce, title)
          
        }>
            <Text>등록하기</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  textTopArea: {
    flex: 0.1,
    borderWidth: 1,
    borderTopLeftRadius: 15,
    borderTopRightRadius: 15,
    paddingLeft: Width * 0.015,
  },
  textBottomArea: {
    flex: 0.9,
    width: Width * 0.9,
    borderWidth: 1,
    borderBottomLeftRadius: 15,
    borderBottomRightRadius: 15,
    borderTopWidth: 0,
    paddingHorizontal: Width * 0.015,
  },
  registerButton: {
    width: Width * 0.3,
    height: Height * 0.07,
    borderWidth: 1,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
  voteContainer: {
    flexDirection: 'row',
    width: Width * 0.8,
  },
  horizontalLine: {
    borderWidth: 0.6,
    marginHorizontal: -Width * 0.015,
    marginTop: 7,
    borderColor: '#d9d9d9',
  },
  checkBox: {
    flexDirection: 'row',
    alignItems: 'center',
  },
});

export default PostActivity;