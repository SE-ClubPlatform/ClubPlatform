import React from 'react';
import {
  View,
  Text,
  Button,
  ScrollView,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import {Dimensions} from 'react-native';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Board({navigation, title}) {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View style={{flex: 1, padding: Width * 0.05}}>
        <Text style={styles.fontStyle}>{title}</Text>
        <View style={{flex: 1}}>
          <ScrollView style={{flex: 1}}>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
            <TouchableOpacity style={styles.postStyle}></TouchableOpacity>
          </ScrollView>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  postStyle: {
    borderRadius: 10,
    height: Height * 0.1,
    backgroundColor: '#FFFFFF',
    elevation: 3,
    margin: 7,
    marginVertical: Height * 0.005,
  },
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
});

export default Board;
