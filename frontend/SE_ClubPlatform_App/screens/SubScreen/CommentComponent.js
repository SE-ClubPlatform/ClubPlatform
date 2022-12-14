import React from 'react';
import {View, Text, Button, Dimensions, Image} from 'react-native';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function CommentComponent({navigation, name, content}) {
  return (
    <View
      style={{
        marginTop: Height * 0.01,
        marginBottom: Height * 0.001,
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
        <Text>{name}</Text>
      </View>
      <View>
        <Text>{content}</Text>
      </View>
    </View>
  );
}

export default CommentComponent;
