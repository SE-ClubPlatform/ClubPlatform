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

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Clubroom({navigation}) {
  const [userCount, setUserCount] = useState(5);

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'white',
      }}>
      <Topbar navigation={navigation} />
      <View style={{padding: Width * 0.05}}>
        <Text style={styles.fontStyle}>동아리방 사용현황</Text>
        <View style={{alignItems: 'center', marginBottom: Height * 0.03}}>
          <View
            style={{
              height: Height * 0.15,
              width: Width * 0.5,
              justifyContent: 'center',
              alignItems: 'center',
              borderRadius: 10,
              backgroundColor: '#F5F5F5',
              marginBottom: Height * 0.03,
            }}>
            <Text style={{}}>현재 입장 인원</Text>
            <Text>{userCount}명</Text>
          </View>

          <TouchableOpacity
            style={{
              height: Height * 0.1,
              width: Width * 0.4,
              justifyContent: 'center',
              alignItems: 'center',
              borderRadius: 25,
              borderWidth: 0.2,
            }}>
            <Text>입장하기</Text>
          </TouchableOpacity>
        </View>
        <View>
          <Text
            style={{
              fontSize: 20,
              marginBottom: Height * 0.02,
              color: 'black',
            }}>
            동아리방 사용 기록
          </Text>
          <ScrollView
            style={{
              height: Height * 0.35,
              borderWidth: 0.1,
              borderRadius: 5,
            }}></ScrollView>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
    color: 'black',
  },
});

export default Clubroom;
