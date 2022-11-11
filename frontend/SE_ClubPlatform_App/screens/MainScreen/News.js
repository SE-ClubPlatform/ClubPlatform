import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function News({navigation}) {
  return (
    <View>
      <Topbar />
      <Text>This is News screen !</Text>
    </View>
  );
}

export default News;
