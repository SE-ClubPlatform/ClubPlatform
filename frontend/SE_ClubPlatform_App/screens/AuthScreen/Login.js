import React, {useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  Image,
  TouchableOpacity,
  Dimensions,
} from 'react-native';

import {TextInput} from 'react-native-gesture-handler';
import LinearGradient from 'react-native-linear-gradient';

import axios from 'axios';

import AsyncStorage from '@react-native-async-storage/async-storage';
import {useTheme} from '@react-navigation/native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

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
    <LinearGradient colors={['#a49ee5', '#5362b2']} style={styles.container}>
      <View style={styles.topArea}>
        {/* <Image
          source={require('../../icons/Login_logo.png')}
          style={{width: wp(25), resizeMode: 'contain'}}
        /> */}
        <Text style={styles.introText}>안녕하세요 :)</Text>
        <Text style={styles.introText}>아주대학교 동아리 관리 플랫폼</Text>
        <View style={{flexDirection: 'row', alignItems: 'flex-end'}}>
          <Text
            style={{
              marginTop: Height * 0.01,
              fontSize: 30,
              fontFamily: 'NanumSquareNeo-eHv',
              color: 'white',
            }}>
            Mo-Ajou !
          </Text>
          <Text style={styles.introText}> 입니다.</Text>
        </View>
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
            <Text style={{color: 'black', fontFamily: 'NanumSquareNeo-bRg'}}>
              로그인
            </Text>
          </TouchableOpacity>
        </View>
        <View style={styles.btnArea}>
          <TouchableOpacity
            style={styles.btn}
            onPress={() => navigation.navigate('Register')}>
            <Text style={{color: 'black', fontFamily: 'NanumSquareNeo-bRg'}}>
              회원가입
            </Text>
          </TouchableOpacity>
        </View>

        <View style={styles.btnArea}>
          <TouchableOpacity>
            <Image
              style={{
                resizeMode: 'stretch',
                width: Width * 1,
                height: Height * 0.105,
              }}
              source={require('../../icons/googleLogin.png')}
            />
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
    paddingHorizontal: Width * 0.04,
  },
  topArea: {
    flex: 1,
    marginTop: Height * 0.15,
    justifyContent: 'center',
    // backgroundColor: 'red',
    marginBottom: Height * 0.05,
  },
  formArea: {
    flex: 2,
    justifyContent: 'center',
    // backgroundColor: '',
    marginBottom: Height * 0.02,
  },
  textFormTop: {
    // borderWidth: 2,
    // borderBottomWidth: 1,
    borderColor: 'black',
    borderRadius: 7,
    width: '100%',
    height: Height * 0.095,
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: 'white',
    marginBottom: Height * 0.01,
    fontFamily: 'NanumSquareNeo-bRg',
  },
  textFormBottom: {
    // borderWidth: 2,
    // borderTopWidth: 1,
    borderColor: 'black',
    borderRadius: 7,
    width: '100%',
    height: Height * 0.095,
    paddingLeft: 10,
    paddingRight: 10,
    backgroundColor: 'white',
    marginBottom: Height * 0.01,
    fontFamily: 'NanumSquareNeo-bRg',
  },
  btnArea: {
    justifyContent: 'center',
    alignItems: 'center',
    height: Height * 0.09,
    marginBottom: Height * 0.01,
  },
  btn: {
    flex: 1,
    width: '100%',
    borderRadius: 7,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#dbe1f9',
  },
  introText: {
    fontSize: 25,
    fontFamily: 'NanumSquareNeoTTF-bRg',
    marginTop: Height * 0.002,
    color: 'white',
  },
});

export default Login;
