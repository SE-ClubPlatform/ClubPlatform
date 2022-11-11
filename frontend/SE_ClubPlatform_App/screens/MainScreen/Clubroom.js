import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function Clubroom({navigation}) {
  return (
    <View>
      <Topbar />
      <Text>This is Clubroom screen !</Text>
    </View>
  );
}

export default Clubroom;
