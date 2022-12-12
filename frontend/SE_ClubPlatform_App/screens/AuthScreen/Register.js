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
import axios from 'axios';
import LinearGradient from 'react-native-linear-gradient';
import userid from '../../recoils/userId';
import userName from '../../recoils/userName';
import userStudentId from '../../recoils/userStudentId';
import userMajor from '../../recoils/userMajor';
import userContact from '../../recoils/userContact';
import {useRecoilState} from 'recoil';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Register({navigation}) {
  const [email, setEmail] = useRecoilState(userid);
  const [password, setPassword] = useState('');
  const [name, setName] = useRecoilState(userName);
  const [studentId, setStudentId] = useRecoilState(userStudentId);
  const [major, setMajor] = useRecoilState(userMajor);
  const [contact, setContact] = useRecoilState(userContact);

  async function registerData(
    email,
    password,
    userName,
    studentId,
    major,
    phoneNumber,
  ) {
    if (!email) {
      alert('이메일을 입력해주세요 .');
      return;
    }
    if (!password) {
      alert('비밀번호를 입력해주세요 .');
      return;
    }
    if (!userName) {
      alert('이름을 입력해주세요 .');
      return;
    }
    if (!studentId) {
      alert('학번을 입력해주세요 .');
      return;
    }
    if (!major) {
      alert('학과를 입력해주세요 .');
      return;
    }
    if (!phoneNumber) {
      alert('연락처를 입력해주세요 .');
      return;
    }

    if (password.length < 8) {
      alert('비밀번호는 8자 이상이어야 합니다 .');
      return;
    }

    try {
      const response = await axios.post(
        'http://sogong-group3.kro.kr/auth/signup',
        {email, password, userName, studentId, major, phoneNumber},
      );
      console.log(response.data);
      if (response.data.state === 200) {
        navigation.navigate('Login');
      } else {
        alert('동일한 이메일이 존재합니다 .');
      }
    } catch (e) {
      alert('동일한 이메일이 존재합니다 .');
      console.log(e);
    }
  }

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
            fontFamily: 'NanumSquareNeo-cBd',
            fontSize: 30,
            color: 'white',
          }}>
          회원가입
        </Text>
      </View>
      <ScrollView style={{flex: 0.94}}>
        <View>
          <Text style={styles.titleText}>Email</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="아주대학교 이메일 (ex.helloworld@ajou.ac.kr)"
            onChangeText={email => setEmail(email)}
            autoCapitalize="none"
          />
        </View>

        <View>
          <Text style={styles.titleText}>Password</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="비밀번호(8자 이상)"
            secureTextEntry={true}
            onChangeText={password => setPassword(password)}
            autoCapitalize="none"
          />
        </View>

        <View>
          <Text style={styles.titleText}>Name</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="이름"
            onChangeText={name => setName(name)}
            autoCapitalize="none"
          />
        </View>
        <View>
          <Text style={styles.titleText}>Student ID</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="학번 (ex.203220845)"
            onChangeText={studentId => setStudentId(studentId)}
            autoCapitalize="none"
          />
        </View>
        <View>
          <Text style={styles.titleText}>Major</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="소속 학과"
            onChangeText={major => setMajor(major)}
            autoCapitalize="none"
          />
        </View>
        <View>
          <Text style={styles.titleText}>Contact</Text>
          <TextInput
            style={styles.inputArea}
            placeholder="연락처 (ex.01022225555)"
            onChangeText={contact => setContact(contact)}
            autoCapitalize="none"
          />
        </View>

        <TouchableOpacity
          style={styles.btn}
          onPress={() =>
            registerData(email, password, name, studentId, major, contact)
          }>
          <Text style={{color: 'black', fontFamily: 'NanumSquareNeo-bRg'}}>
            회원가입
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
    height: Height * 0.075,
    backgroundColor: 'white',
    marginBottom: Height * 0.015,
    paddingHorizontal: Width * 0.03,
    fontFamily: 'NanumSquareNeo-bRg',
  },
  titleText: {
    color: 'white',
    fontFamily: 'NanumSquareNeo-bRg',
  },
});
export default Register;
