import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function Home({navigation}) {
  return (
    <View>
      <Topbar navigation={navigation} />
      <Text>This is Home screen !</Text>
    </View>
  );
}

export default Home;
