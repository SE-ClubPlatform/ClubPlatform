/* eslint-disable react/self-closing-comp */
/* eslint-disable react-native/no-inline-styles */
import React, {useState} from 'react';
import {
  View,
  Text,
  Button,
  TouchableOpacity,
  Dimensions,
  StyleSheet,
} from 'react-native';
import {ScrollView} from 'react-native-gesture-handler';
import Topbar from '../Bar/Topbar';
import {heads, bodyDatas} from '../SubScreen/tableComponent';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function MemberList({navigation}) {
  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'white',
      }}>
      <Topbar navigation={navigation} />
      <View style={{padding: Width * 0.05, paddingBottom: Width * 0.02}}>
        <Text style={styles.fontStyle}>동아리원 관리</Text>
      </View>
      <View style={styles.tag_container}>
        <Text>탭 위치</Text>
      </View>
      <ScrollView style={styles.context_container}></ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
    color: 'black',
  },
  tag_container: {
    //flex: 0.5,
    height: 40,
    flexDirection: 'row',
    backgroundColor: '#F5F5F5',
    marginLeft: 30,
    marginRight: 30,
  },
  context_container: {
    backgroundColor: '#777777',
    flex: 1,
    marginLeft: 15,
    marginRight: 15,
    marginBottom: 40,
    marginTop: 24,
    //height: Height * 0.75,
    borderWidth: 0.1,
    borderRadius: 5,
  },
});

export default MemberList;
