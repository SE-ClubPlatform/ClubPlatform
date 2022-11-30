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
import Topbar from '../Bar/Topbar';
import Home_Contents from '../Container/Home_Contents';
import Home_Profile from '../Container/Home_Profile';


const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Home({navigation}) {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <ScrollView>
        <Home_Profile/>
        <Home_Contents title="공지사항"/>
        <Home_Contents title="활동 모아보기"/>
        <Home_Contents title="소모임 모집"/>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  container_title: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
  container_sub: {
    flexDirection: 'row',
    alignItems: 'center',
    margin: 3,
    padding: 5,
  },
  container_right: {
    flex: 2,
    flexDirection: 'row',
  },
  clubImg: {
    width: 120,
    height: 120,
    borderRadius: 10,
    marginRight: 10,
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
    paddingBottom: 30,
  },
  gray_card: {
    backgroundColor: '#F5F5F5',
    flexDirection: 'row',
    flex: 1,
    borderRadius: 7,
    marginLeft: 5,
    marginRight: 5,
    marginBottom: 5,
    alignSelf: 'baseline',
    marginTop: 10,
    padding: 5,
    justifyContent: 'space-between',
    alignItems: 'baseline',
  },
  cardTitle: {
    flex: 1,
    alignContent: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
  gray_card_title: {
    flex: 1,
    marginRight: 10,
  },
  gray_card_content: {
    flex: 1,
    marginLeft: 15,
  },
});

export default Home;
