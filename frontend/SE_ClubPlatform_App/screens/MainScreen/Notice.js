import React from 'react';
import {View, Text, Button, StyleSheet} from 'react-native';
import Topbar from '../Bar/Topbar';
import Board from '../SubScreen/Board';
import {Dimensions} from 'react-native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Notice({navigation}) {
  return (
    // <View style={{flex: 1, backgroundColor: 'white'}}>
    //   <Topbar navigation={navigation} />
    //   <View style={{flex: 1, margin: Width * 0.05}}>
    //     <Text style={styles.fontStyle}>공지사항 📢</Text>
    //     <Board></Board>
    //   </View>
    // </View>
    <Board navigation={navigation} title={'공지사항 📢'} />
  );
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
});

export default Notice;
