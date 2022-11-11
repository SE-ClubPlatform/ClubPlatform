import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function Group({navigation}) {
  return (
    <View>
      <Topbar />
      <Text>This is Group screen !</Text>
    </View>
  );
}

export default Group;
