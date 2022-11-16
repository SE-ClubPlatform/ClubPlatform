import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function Notice({navigation}) {
  return (
    <View>
      <Topbar navigation={navigation} />
      <Text>This is Notice screen !</Text>
    </View>
  );
}

export default Notice;
