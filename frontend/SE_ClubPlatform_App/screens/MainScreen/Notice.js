import React, {useEffect, useState} from 'react';
import {View, Text, Button, StyleSheet} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';
import {Dimensions} from 'react-native';
import userToken from '../../recoils/userToken';
import axios from 'axios';
import {useRecoilState, useRecoilValue} from 'recoil';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Notice({navigation}) {
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [postData, setPostData] = useState();

  async function getData(token, clubId) {
    try {
      console.log(token);
      console.log(clubId);
      const response = await axios.get(
        `http://sogong-group3.kro.kr/club/${clubId}/notice`,
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
    <Board navigation={navigation} data={postData} boardType={'notice'} />
  ) : (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar />
    </View>
  );
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
});

export default Notice;
