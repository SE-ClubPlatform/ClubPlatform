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


function Home_Contents({navigation, title}) {
    return (
        <View>
            <View style={styles.card}>
          <View style={styles.container_title}>
            <Text style={styles.cardTitle}>{title}</Text>
            <TouchableOpacity
              activeOpacity={0.8}
              onPress={() => navigation.navigate('Notice')}
              style={{
                flex: 1,
                flexDirection: 'row',
                justifyContent: 'space-between',
                alignItems: 'center',
              }}
              >
              <Text style={{margin: 5}}>더 보기</Text>
              <Image
                style={{width: 10, height: 13}}
                resizeMode="stretch"
                source={require('../../icons/ic_right_arrow.png')}
              />
            </TouchableOpacity>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight: 15}}>
              <Text>1</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 1111</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight: 15}}>
              <Text>2</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 2222</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight: 15}}>
              <Text>3</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 3333</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight: 15}}>
              <Text>4</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 4444</Text>
            </View>
          </View>
          <View style={styles.container_sub}>
            <View style={{marginRight: 15}}>
              <Text>5</Text>
            </View>
            <View>
              <Text>공지사항 제목만 보여줄 예정입니다 5555</Text>
            </View>
          </View>
        </View>
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
export default Home_Contents;
