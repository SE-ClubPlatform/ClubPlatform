import React, {useState} from 'react';
import {View, Text, StyleSheet, Image, TouchableOpacity} from 'react-native';
import {TextInput} from 'react-native-gesture-handler';
import LinearGradient from 'react-native-linear-gradient';
import {
  widthPercentageToDP as wp,
  heightPercentageToDP as hp,
} from 'react-native-responsive-screen';

import axios from 'axios';

import AsyncStorage from '@react-native-async-storage/async-storage';
import {useTheme} from '@react-navigation/native';

function Login({navigation}) {
  const [userId, setUserId] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [errortext, setErrortext] = useState('');

  //   async function postData(id, password) {
  //     setErrortext('');
  //     if (!id) {
  //       alert('아이디를 입력해주세요 .');
  //       return;
  //     }
  //     if (!password) {
  //       alert('비밀번호를 입력해주세요 .');
  //       return;
  //     }
  //     setLoading(true);

  //     try {
  //       const response = await axios.post('http://10.0.2.2:80/signin', {
  //         id,
  //         password,
  //       });

  //       // console.log(response.data);
  //       if (response.data.msg === 'login success') {
  //         AsyncStorage.setItem('user_id', userId);
  //         setLoading(false);
  //         navigation.replace('Main');
  //       } else {
  //         alert('아이디와 비밀번호를 다시 확인해주세요 .');
  //         setLoading(false);
  //       }
  //     } catch (e) {
  //       setLoading(false);
  //       console.log(e);
  //     }
  //   }

  return (
    <LinearGradient colors={['#FFCDD2', '#FFAAB3']} style={styles.container}>
      <View style={styles.topArea}>
        <Image
          source={require('../../icons/Login_logo.png')}
          style={{width: wp(25), resizeMode: 'contain'}}
        />
      </View>

      <View style={styles.formArea}>
        <TextInput
          style={styles.textFormTop}
          placeholder="ID"
          onChangeText={userId => setUserId(userId)}
          autoCapitalize="none"
        />
        <TextInput
          style={styles.textFormBottom}
          placeholder="PASSWORD"
          onChangeText={userPassword => setUserPassword(userPassword)}
          autoCapitalize="none"
          secureTextEntry={true}
        />
      </View>
      <View style={{flex: 0.75}}>
        <View style={styles.btnArea}>
          <TouchableOpacity
            style={styles.btn}
            // onPress={() => postData(userId, userPassword)}
            onPress={() => navigation.navigate('HomeStack')}>
            <Text style={{color: 'white'}}>로그인</Text>
          </TouchableOpacity>
        </View>
        <View style={styles.btnArea}>
          <TouchableOpacity
            style={styles.btn}
            onPress={() => navigation.navigate('Register')}>
            <Text style={{color: 'white'}}>회원가입</Text>
          </TouchableOpacity>
        </View>
      </View>
      <View style={{flex: 3}} />
    </LinearGradient>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: 'white',
    paddingLeft: wp(7),
    paddingRight: wp(7),
  },
  topArea: {
    flex: 1,
    marginTop: wp(30),
    justifyContent: 'center',
    // backgroundColor: 'red',
    marginBottom: wp(7),
  },
  titleArea: {
    flex: 1,
    // backgroundColor: 'white',
    justifyContent: 'center',
    paddingTop: wp(0.3),
  },
  textArea: {
    flex: 1,
    // backgroundColor: 'green',
    justifyContent: 'center',
    paddingTop: wp(3),
  },
  text: {
    fontSize: wp('4%'),
  },
  formArea: {
    flex: 2,
    justifyContent: 'center',
    // backgroundColor: '',
    marginBottom: 2,
  },
  textFormTop: {
    borderWidth: 2,
    borderBottomWidth: 1,
    borderColor: 'black',
    borderTopLeftRadius: 7,
    borderTopRightRadius: 7,
    width: '100%',
    height: hp(9),
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: 'white',
  },
  textFormBottom: {
    borderWidth: 2,
    borderTopWidth: 1,
    borderColor: 'black',
    borderBottomLeftRadius: 7,
    borderBottomRightRadius: 7,
    width: '100%',
    height: hp(9),
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: 'white',
  },
  btnArea: {
    justifyContent: 'center',
    alignItems: 'center',
    height: hp(8),
    paddingBottom: hp(0.5),
  },
  btn: {
    flex: 1,
    width: '100%',
    borderRadius: 7,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'black',
  },
});

export default Login;
