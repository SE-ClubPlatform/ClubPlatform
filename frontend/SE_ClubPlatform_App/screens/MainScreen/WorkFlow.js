import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  Button,
  ScrollView,
  StyleSheet,
  TextInput,
  Image,
  Dimensions,
} from 'react-native';
import {TouchableOpacity} from 'react-native-gesture-handler';
import {back} from 'react-native/Libraries/Animated/Easing';
import {useRecoilState} from 'recoil';
import userToken from '../../recoils/userToken';
import axios from 'axios';
import Topbar from '../Bar/Topbar';
import ActivityCard from '../Container/ActivityCard';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function WorkFlow({navigation}) {
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [workList, setWorkList] = useState();

  async function getWork(token, clubId) {
    try {
      const response = await axios.get(
        "http://sogong-group3.kro.kr/club/" + clubId + "/work/all",
        {
          headers: {
            Authorization: token,
          },
        },
      )
      setWorkList(response.data.content)
      console.log(response.data.content)
    } catch (e) {
      // alert('아이디와 비밀번호를 다시 확인해주세요 .');
      // setLoading(false);
      // console.log(e);
    }
  }

  useEffect(()=>{
    getWork(userToken_R, 1)
  }, [])
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View
        style={{
          margin: Width * 0.05,
          flexDirection: 'row',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}>
        <Text style={styles.fontStyle}>활동별 진행상황 보기</Text>
        <TouchableOpacity onPress={() => navigation.navigate('CreateWorkFlow')}>
          <Image
            source={require('../../icons/ic_add.png')}
            style={{
              width: Width * 0.06,
              height: Width * 0.06,
            }}
          />
        </TouchableOpacity>
      </View>
      <ScrollView>
        {workList?workList.map(function(a){
          return (<ActivityCard workInfo={a}/>)
        }):null}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  btn_gray: {
    paddingHorizontal: 30,
    paddingVertical: 10,
    backgroundColor: '#D9D9D9',
    borderRadius: 10,
  },
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
  container: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  container_title: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  container_sub: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    margin: 3,
    padding: 5,
  },
  card: {
    backgroundColor: '#fff',
    flex: 1,
    borderRadius: 10,
    marginLeft: 20,
    marginRight: 20,
    marginBottom: 10,
    marginTop: 10,
    elevation: 3,
    padding: 20,
  },
  gray_card: {
    backgroundColor: '#F5F5F5',
    flexDirection: 'row',
    flex: 0.7,
    borderRadius: 7,
    marginLeft: 5,
    marginRight: 5,
    marginBottom: 10,
    alignSelf: 'baseline',
    marginTop: 10,
    padding: 5,
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  cardTitle: {
    flex: 1,
    alignContent: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  gray_card_title: {
    flex: 1,
  },
  gray_card_content: {
    flex: 1,
  },
});

export default WorkFlow;
