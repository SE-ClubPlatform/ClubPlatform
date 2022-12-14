import React from 'react';
import {View, Text, Button, Dimensions, Image} from 'react-native';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function CommentComponent({navigation}) {
  return (
    <View
      style={{
        marginVertical: Height * 0.01,
        backgroundColor: '#f2f2f2',
        borderRadius: 10,
        padding: 10,
      }}>
      <View
        style={{
          flexDirection: 'row',
          alignItems: 'center',
          marginBottom: Height * 0.01,
        }}>
        <Image
          style={{
            width: Width * 0.07,
            height: Width * 0.07,
            resizeMode: 'stretch',
            marginRight: Width * 0.01,
          }}
          source={require('../../icons/User.png')}
        />
        <Text>GM지영</Text>
      </View>
      <View>
        <Text>넵 확인하였습니다 !</Text>
        <Text style={{fontSize: 14}}>11/05 18:53</Text>
      </View>
    </View>
  );
}

export default CommentComponent;
