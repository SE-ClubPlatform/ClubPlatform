import React from 'react';
import {
  StyleSheet,
  View,
  Text,
  StatusBar,
  Image,
  TouchableOpacity,
  Dimensions,
  ScrollView,
} from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Topbar({navigation}) {
  return (
    <View style={styles.block}>
      <Text style={styles.text}>ClubPlatform</Text>
      <View style={styles.StatusBarIcon}>
        <TouchableOpacity
          style={{marginRight: Width * 0.85, marginTop: Width * 0.005}}
          activeOpacity={0.7}
          onPress={() => navigation.pop()}>
          <Image source={require('../../icons/Back.png')} />
        </TouchableOpacity>

        <TouchableOpacity
          activeOpacity={0.7}
          onPress={() => navigation.navigate('Notice')}>
          <Image source={require('../../icons/Alarm.png')} />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  statusBarPlaceholder: {
    backgroundColor: '#FFCDD2',
  },
  block: {
    backgroundColor: '#FFCDD2',
    paddingVertical: Height * 0.0146,
    borderBottomRightRadius: 23,
    marginBottom: Height * 0.006,
    flexDirection: 'row',
    justifyContent: 'center',
    alignContent: 'center',
  },
  text: {
    fontSize: 18,
    fontWeight: '900',
    color: 'white',
    fontFamily: 'Happiness-Sans-Bold',
    // paddingRight: Width * 0.226,
  },

  StatusBarIcon: {
    position: 'absolute',
    flexDirection: 'row',
    right: Width * 0.03,
    top: Height * 0.015,
  },
});

export default Topbar;
