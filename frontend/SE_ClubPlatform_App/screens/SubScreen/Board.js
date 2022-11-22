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
import PostComponent from './PostComponent';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Board({navigation, title}) {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View style={{flex: 1, margin: Width * 0.05}}>
        <View
          style={{
            flexDirection: 'row',
            justifyContent: 'space-between',
            paddingRight: Width * 0.02,
            marginBottom: Height * 0.02,
          }}>
          <Text style={styles.fontStyle}>{title}</Text>
          <TouchableOpacity
            style={styles.postButton}
            onPress={() => navigation.navigate('Post')}>
            <Text>글쓰기</Text>
          </TouchableOpacity>
        </View>
        <View style={{flex: 1}}>
          <ScrollView style={{flex: 1}}>
            <PostComponent />
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
  postButton: {
    width: Width * 0.2,
    height: Height * 0.05,
    borderWidth: 0.3,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: Height * 0.007,
  },
});

export default Board;
