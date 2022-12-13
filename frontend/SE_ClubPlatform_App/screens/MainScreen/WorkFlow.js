import React from 'react';
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
import * as Progress from 'react-native-progress';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function WorkFlow({navigation}) {
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
        <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>활동 11</Text>
            <View style={styles.gray_card}>
              <View>
                <Text style={styles.gray_card_title}>진행단계</Text>
              </View>
              <View>
                <Text style={styles.gray_card_content}>인원모집</Text>
              </View>
            </View>
          </View>
          <View style={styles.container_sub}>
            <Text>
              동아리 활동에 대한 개괄적인 설명이 기재되는 칸입니다. 동아리
              활동에 대한 개괄적인 설명이 기재되는 칸입니다.{' '}
            </Text>
          </View>
          <View style={styles.container_sub}>
            <TouchableOpacity>
              <View style={styles.btn_gray}>
                <Text>투표하기</Text>
              </View>
            </TouchableOpacity>
            <TouchableOpacity style={styles.container_sub}>
              <View style={styles.btn_gray}>
                <Text>공지보기</Text>
              </View>
            </TouchableOpacity>
          </View>
          <Progress.Bar
            progress={0.8}
            width={Width * 0.8}
            height={Height * 0.012}
            borderRadius={10}
            color={'#B2AC8A'}
          />
        </View>
        <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>활동 22</Text>
            <View style={styles.gray_card}>
              <View>
                <Text style={styles.gray_card_title}>진행단계</Text>
              </View>
              <View>
                <Text style={styles.gray_card_content}>내부검토</Text>
              </View>
            </View>
          </View>
          <View style={styles.container_sub}>
            <Text>
              동아리 활동에 대한 개괄적인 설명이 기재되는 칸입니다. 동아리
              활동에 대한 개괄적인 설명이 기재되는 칸입니다.{' '}
            </Text>
          </View>
          <View style={styles.container_sub}>
            <TouchableOpacity>
              <View style={styles.btn_gray}>
                <Text>투표하기</Text>
              </View>
            </TouchableOpacity>
            <TouchableOpacity style={styles.container_sub}>
              <View style={styles.btn_gray}>
                <Text>공지보기</Text>
              </View>
            </TouchableOpacity>
          </View>
          <Progress.Bar
            progress={0.4}
            width={Width * 0.8}
            height={Height * 0.012}
            borderRadius={10}
            color={'#B2AC8A'}
          />
        </View>
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
