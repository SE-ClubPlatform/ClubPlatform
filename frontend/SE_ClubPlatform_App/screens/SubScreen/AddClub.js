import React from 'react';
import {View, Text, Button} from 'react-native';
import Topbar from '../Bar/Topbar';

function AddClub({navigation}) {
  return (
    <View>
      <Topbar navigation={navigation} />
      <Text>This is AddClub screen !</Text>
    </View>
  );
}

export default AddClub;
