import React from 'react';
import {View, Text, Button, Image, StyleSheet} from 'react-native';
import {TouchableOpacity} from 'react-native-gesture-handler';
import Topbar from '../Bar/Topbar';
import {Dimensions} from 'react-native';
import {Platform} from 'react-native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Main({navigation}) {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar />
      <View style={{margin: Width * 0.05}}>
        <Text style={{fontSize: 25, marginBottom: Height * 0.01}}>
          내 동아리
        </Text>
        <View style={{flexDirection: 'row'}}>
          <TouchableOpacity
            style={{...styles.myClubButton, marginRight: Width * 0.01}}
            onPress={() => navigation.navigate('ClubMain')}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/DoiT.png')}
            />
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.myClubButton}
            onPress={() => navigation.navigate('ClubMain')}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/Ggong.png')}
            />
          </TouchableOpacity>
        </View>
      </View>
      <View></View>
    </View>
  );
}

const styles = StyleSheet.create({
  clubImage: {
    width: Width * 0.45,
    height: Height * 0.2,
  },
  myClubButton: {
    elevation: 3,
    borderRadius: 10,
  },
});

export default Main;
