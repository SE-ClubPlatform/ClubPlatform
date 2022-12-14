import React, {useEffect, useState} from 'react';
import {
  View,
  Text,
  Button,
  Dimensions,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import {TextInput} from 'react-native-gesture-handler';
import Topbar from '../Bar/Topbar';
import userToken from '../../recoils/userToken';
import {useRecoilState, useRecoilValue} from 'recoil';
import axios from 'axios';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Post({navigation, route}) {
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [Title, setTitle] = useState('');
  const [Content, setContent] = useState('');

  async function postData(token, clubId, Title, Content, isFinish) {
    try {
      // console.log(token);
      // console.log(clubId);
      // console.log(Title);
      // console.log(Content);
      // console.log(route.params.boardtype);
      const response = await axios.post(
        `http://sogong-group3.kro.kr/club/${clubId}/${route.params.boardtype}`,
        {
          headers: {
            Authorization: token,
          },
          Title,
          Content,
          isFinish,
        },
      );
      if (response) {
        console.log(response);
      }
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    console.log(route.params.boardtype);
  }, []);

  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View style={{flex: 1, margin: Width * 0.05}}>
        <View style={{flex: 0.1, justifyContent: 'center'}}>
          <Text style={{fontSize: 28}}>게시물 작성</Text>
        </View>
        <View
          style={{
            flex: 0.8,
          }}>
          <View style={styles.textTopArea}>
            <TextInput
              style={{fontSize: 15}}
              onChangeText={Title => setTitle(Title)}
              placeholder="제목을 입력해주세요"
              autoCapitalize="none"></TextInput>
          </View>
          <View style={styles.textBottomArea}>
            <TextInput
              style={{fontSize: 15}}
              onChangeText={Content => setContent(Content)}
              placeholder="내용을 입력해주세요"
              autoCapitalize="none"></TextInput>
          </View>
        </View>
        <View
          style={{
            flex: 0.1,
            justifyContent: 'center',
            alignItems: 'flex-end',
          }}>
          <TouchableOpacity
            onPress={() =>
              postData(`Bearer ${userToken_R}`, 1, Title, Content, false)
            }
            style={styles.registerButton}>
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
    borderWidth: 1,
    borderBottomLeftRadius: 15,
    borderBottomRightRadius: 15,
    borderTopWidth: 0,
    paddingLeft: Width * 0.015,
  },
  registerButton: {
    width: Width * 0.3,
    height: Height * 0.07,
    borderWidth: 1,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default Post;
