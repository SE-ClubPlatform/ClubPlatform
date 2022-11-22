import React, {useState} from 'react';
import {View, Text, Button, StyleSheet, Dimensions, Image} from 'react-native';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function PostComponent({navigation}) {
  const [name, setName] = useState('GM우현');
  const [date, setDate] = useState('2022/11/04');
  const [time, setTime] = useState('09:15');
  const [content, setContent] = useState('동아리방 사용 공지 사');

  return (
    <View style={styles.postStyle}>
      <View style={styles.helfArea}>
        <View style={{flexDirection: 'row', alignItems: 'center'}}>
          <Image
            style={styles.userIcon}
            source={require('../../icons/User.png')}
            resizeMode="contain"
          />
          <Text style={{fontSize: 15, fontWeight: 'bold'}}>{name}</Text>
        </View>
        <View style={{alignItems: 'flex-end'}}>
          <Text style={styles.dateStyle}>{date}</Text>
          <Text style={styles.dateStyle}>{time}</Text>
        </View>
      </View>
      <View style={styles.helfArea}>
        <Text></Text>
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
    paddingHorizontal: Width * 0.02,
    paddingVertical: Height * 0.01,
  },
  helfArea: {
    flex: 0.5,
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  userIcon: {
    width: Width * 0.055,
    height: Height * 0.055,
    marginRight: Width * 0.01,
  },
  dateStyle: {
    fontSize: 13,
  },
});

export default PostComponent;
