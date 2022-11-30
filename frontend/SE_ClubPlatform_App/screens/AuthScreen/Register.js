import React, {useReducer, useState} from 'react';
import {
  StyleSheet,
  View,
  Text,
  Image,
  TouchableOpacity,
  TextInput,
  Keyboard,
  Modal,
  ScrollView,
  Platform,
  Dimensions,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Register({navigation}) {
  return (
    <LinearGradient colors={['#a49ee5', '#5362b2']} style={styles.container}>
      <View
        style={{
          flex: 0.06,
          marginTop: Height * 0.08,
          marginBottom: Height * 0.02,
        }}>
        <Text
          style={{
            fontFamily: 'NanumSquareNeo-eHv',
            fontSize: 30,
            color: 'white',
          }}>
          회원가입
        </Text>
      </View>
      <ScrollView style={{flex: 0.94}}>
        <View>
          <Text style={styles.titleText}>User ID</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="아이디(5자 이상, 영문, 숫자 포함)"
          />
        </View>

        <View>
          <Text style={styles.titleText}>Password</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="비밀번호(8자 이상)"
          />
        </View>
        {/* <View>
          <Text style={{color: 'white'}}>Password Check</Text>
          <TextInput style={styles.inputArea} placeholder="비밀번호 확인" />
        </View> */}
        <View>
          <Text style={styles.titleText}>Name</Text>
          <TextInput style={styles.inputArea} placeholder="이름" />
        </View>
        <View>
          <Text style={styles.titleText}>Student ID</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="학번 (ex.203220845)"
          />
        </View>
        <View>
          <Text style={styles.titleText}>Major</Text>
          <TextInput style={styles.inputArea} placeholder="소속 학과" />
        </View>
        <View>
          <Text style={styles.titleText}>Contact</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="연락처 (ex.0102225555)"
          />
        </View>
        <View>
          <Text style={styles.titleText}>Email</Text>
          <TextInput style={styles.inputArea} placeholder="이메일" />
        </View>

        <TouchableOpacity style={styles.btn}>
          <Text style={{color: 'black', fontFamily: 'NanumSquareNeo-bRg'}}>
            로그인
          </Text>
        </TouchableOpacity>
      </ScrollView>
    </LinearGradient>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: 'white',
    paddingHorizontal: Width * 0.05,
  },
  topArea: {
    flex: 1,
    marginTop: Height * 0.2,
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
  },
  btnArea: {
    justifyContent: 'center',
    alignItems: 'center',
    height: Height * 0.095,
    marginBottom: Height * 0.01,
  },
  btn: {
    flex: 1,
    width: '100%',
    borderRadius: 7,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#dbe1f9',
    height: Height * 0.09,
    marginTop: Height * 0.02,
  },
  introText: {
    fontSize: 25,
    fontFamily: 'NanumSquareNeoTTF-bRg',
    marginTop: Height * 0.002,
  },
  textArea: {
    flex: 0.4,
    justifyContent: 'center',
  },
  inputArea: {
    borderRadius: 7,
    width: '100%',
    height: Height * 0.065,
    backgroundColor: 'white',
    marginBottom: Height * 0.01,
    paddingHorizontal: Width * 0.03,
    fontFamily: 'NanumSquareNeo-bRg',
  },
  titleText: {
    color: 'white',
    fontFamily: 'NanumSquareNeo-bRg',
  },
});
export default Register;
