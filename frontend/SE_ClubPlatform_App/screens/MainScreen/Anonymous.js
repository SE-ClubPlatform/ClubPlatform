import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';

const postData = [
  {
    title: '동아리방 사용 관련 공지사항',
    author: 'GM우현',
    date: '2022/11/04',
    time: '09:15',
    commentCount: 5,
  },
  {
    title: '동아리방 사용 관련 공지사항',
    author: 'GM우현',
    date: '2022/11/04',
    time: '09:15',
    commentCount: 5,
  },
];

function Anonymous({navigation}) {
  return <Board navigation={navigation} boardType={'anonymous'} />;
}

export default Anonymous;
