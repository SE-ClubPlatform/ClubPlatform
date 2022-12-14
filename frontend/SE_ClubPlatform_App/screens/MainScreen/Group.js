import React from 'react';
import {View, Text, Button, Dimensions} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';
import userToken from '../../recoils/userToken';
import axios from 'axios';
import {useRecoilState, useRecoilValue} from 'recoil';
import {useState} from 'react';
import {useEffect} from 'react';

function Group({navigation}) {
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [postData, setPostData] = useState();

  async function getData(token, clubId) {
    try {
      console.log(token);
      console.log(clubId);
      const response = await axios.get(
        `http://sogong-group3.kro.kr/club/${clubId}/community`,
        {
          headers: {
            Authorization: token,
          },
        },
      );
      if (response) {
        console.log('Hello?');
        console.log(response.data);
        setPostData(response.data);
      }
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    getData(`Bearer ${userToken_R}`, 1);
  }, []);

  return postData ? (
    <Board navigation={navigation} data={postData} boardType={'group'} />
  ) : (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar />
    </View>
  );
}

export default Group;
