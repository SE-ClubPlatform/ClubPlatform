import React from 'react';
import {View, Text, Button, StyleSheet} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';
import {Dimensions} from 'react-native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Notice({navigation}) {
  return <Board navigation={navigation} boardType={'notice'} club_id={12} />;
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
});

export default Notice;
