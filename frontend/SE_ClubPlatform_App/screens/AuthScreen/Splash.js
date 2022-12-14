import React, {useState, useEffect} from 'react';
import {ActivityIndicator, View, StyleSheet, Image} from 'react-native';
import {
  widthPercentageToDP as wp,
  heightPercentageToDP as hp,
} from 'react-native-responsive-screen';

import AsyncStorage from '@react-native-async-storage/async-storage';
import userid from '../../recoils/userId';
import userToken from '../../recoils/userToken';
import {useRecoilState} from 'recoil';

const Splash = ({navigation}) => {
  const [animating, setAnimating] = useState(true);
  const [userId_R, setUserId_R] = useRecoilState(userid);
  const [userToken_R, setUserToken_R] = useRecoilState(userToken);

  useEffect(() => {
    setTimeout(() => {
      setAnimating(false);
      AsyncStorage.getItem('user_id').then(value => {
        console.log(value);
        setUserId_R(value);
        AsyncStorage.getItem(`${value}_token`).then(value2 => {
          console.log(value2);
          setUserToken_R(value2);
          navigation.replace(value === null ? 'Auth' : 'HomeStack');
          //navigation.replace('Auth');
        });
      });
    }, 3000);
  }, []);

  return (
    <View style={styles.container}>
      <Image
        source={require('../../images/Temp.png')}
        style={{width: wp(75), resizeMode: 'contain', margin: 30}}
      />
      <ActivityIndicator
        animating={animating}
        color="#6990F7"
        size="large"
        style={styles.ActivityIndicator}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#a49ee5',
  },
  ActivityIndicator: {
    alignItems: 'center',
    height: 80,
  },
});

export default Splash;
