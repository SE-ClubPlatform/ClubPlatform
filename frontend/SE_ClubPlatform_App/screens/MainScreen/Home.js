import React from 'react';
import {View, Text, Button, ScrollView, StyleSheet, TextInput, Image, Dimensions} from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { back } from 'react-native/Libraries/Animated/Easing';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Home({navigation}) {
  return (
    <View style= {{flex:1,}}>
      <Topbar navigation={navigation} />
      <ScrollView>
        <View style={styles.card}>
          <View style={styles.container}>
            <Text style={styles.cardTitle}>Do-it!</Text>
            <View style={styles.container_right}>
              <View style={styles.gray_card}>
                <View>
                  <Text style={styles.gray_card_title}>회장</Text>
                </View>
                <View>
                  <Text style={styles.gray_card_content}>김두잇</Text>
                </View> 
              </View>
              <View style={styles.gray_card}>
                <View>
                  <Text style={styles.gray_card_title}>부원</Text>
                </View>
                <View>
                  <Text style={styles.gray_card_content}>00명</Text>
                </View> 
              </View>
            </View>
          </View>
          <View style={styles.container}>
              <Image
                style={styles.clubImg}
                resizeMode="stretch"
                source={require('../../images/DoiT.png')}
              />
              <View style={{flex : 1}}>
                  <Text style={styles.captain_name}>안녕하세요 ! 저희는 아주대학교 웹개발 동아리 Do-it! 입니다 :)</Text>
                </View>  
            </View>
        </View>
        <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>공지사항</Text>
            <TouchableOpacity
              style={{
                flex:1,
                flexDirection : "row",
                justifyContent : "space-between",
                alignItems :"center"}}>
              <Text style={{margin : 5}}>더 보기</Text>
              <Image
              style={{width : 10, height : 13,}}
              resizeMode='stretch'
              source={require('../../icons/ic_right_arrow.png')}
              />
            </TouchableOpacity>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>1</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 1111</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>2</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 2222</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>3</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 3333</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>4</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 4444</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>5</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 5555</Text>
            </View>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>활동 모아보기</Text>
            <TouchableOpacity
              style={{
                flex:1,
                flexDirection : "row",
                justifyContent : "space-between",
                alignItems :"center"}}>
              <Text style={{margin : 5}}>더 보기</Text>
              <Image
              style={{width : 10, height : 13,}}
              resizeMode='stretch'
              source={require('../../icons/ic_right_arrow.png')}
              />
            </TouchableOpacity>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>1</Text>
            </View>
            <View>
              <Text>활동명만 보여줄 예정입니다 1111</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>2</Text>
            </View>
            <View>
              <Text>활동명만 보여줄 예정입니다 2222</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>3</Text>
            </View>
            <View>
              <Text>활동명만 보여줄 예정입니다 3333</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>4</Text>
            </View>
            <View>
              <Text>활동명만 보여줄 예정입니다 4444</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>5</Text>
            </View>
            <View>
              <Text>활동명만 보여줄 예정입니다 5555</Text>
            </View>
          </View>
        </View>
        <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>소모임 모집</Text>
            <TouchableOpacity
              style={{
                flex:1,
                flexDirection : "row",
                justifyContent : "space-between",
                alignItems :"center"}}>
              <Text style={{margin : 5}}>더 보기</Text>
              <Image
              style={{width : 10, height : 13,}}
              resizeMode='stretch'
              source={require('../../icons/ic_right_arrow.png')}
              />
            </TouchableOpacity>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>1</Text>
            </View>
            <View>
              <Text>소모임 모집 제목만 보여줄 예정입니다 1111</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>2</Text>
            </View>
            <View>
              <Text>소모임 모집 제목만 보여줄 예정입니다 2222</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>3</Text>
            </View>
            <View>
              <Text>소모임 모집 제목만 보여줄 예정입니다 3333</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>4</Text>
            </View>
            <View>
              <Text>소모임 모집 제목만 보여줄 예정입니다 4444</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight : 15}}>
              <Text>5</Text>
            </View>
            <View>
              <Text>소모임 모집 제목만 보여줄 예정입니다 5555</Text>
            </View>
          </View>
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container :{
    flexDirection:"row",
    justifyContent: "space-between",
    alignItems : "center",
  },
  container_title :{
    flexDirection:"row",
    justifyContent: "space-between",
    marginBottom : 20,
  },
  container_sub : {
    flexDirection: 'row',
    alignItems: 'center',
    margin : 3,
    padding: 5,
  },
  container_right :{
    flex : 2,
    flexDirection:"row",
  },
  clubImg :{
    width : 120,
    height : 120,
    borderRadius: 10,
    marginRight : 10,
  },
  card: {
    backgroundColor: '#fff',
    flex: 1,
    borderRadius : 10,
    marginLeft : 20,
    marginRight : 20,
    marginBottom : 10,
    marginTop : 10,
    elevation : 3,
    padding : 20,
    paddingBottom : 30,
  },
  gray_card: {
    backgroundColor: '#F5F5F5',
    flexDirection:"row",
    flex: 1,
    borderRadius : 7,
    marginLeft : 5,
    marginRight : 5,
    marginBottom : 10,
    alignSelf:"baseline",
    marginTop : 10,
    padding : 5,
    justifyContent: "space-between",
    alignItems: "center"
    
  },
  cardTitle : {
    flex : 1,
    alignContent :"center",
    fontSize : 20,
    fontWeight:"bold",
  },
  gray_card_title : {
    flex : 1,
  },
  gray_card_content : {
    flex : 1,
  },
})

export default Home;
