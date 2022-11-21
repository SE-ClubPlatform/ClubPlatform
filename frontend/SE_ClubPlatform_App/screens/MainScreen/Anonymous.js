import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';

function Anonymous({navigation}) {
  return <Board navigation={navigation} title={'익명 신문고 🥁'} />;
}

export default Anonymous;
