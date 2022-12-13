import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';

function Group({navigation}) {
  return <Board navigation={navigation} boardType={'group'} />;
}

export default Group;
