import React, { useState } from 'react';
import {View, Text, Button, Image, StyleSheet, Modal, Fressable} from 'react-native';
import {TouchableOpacity} from 'react-native';
import Topbar from '../Bar/Topbar';
import {Dimensions} from 'react-native';
import {Platform} from 'react-native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Main({navigation}) {
  const [modalVisible, setModalVisible] = useState(false);
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View style={{margin: Width * 0.05, flex: 0.6}}>
        <Text style={styles.fontStyle}>내 동아리</Text>
        <View style={{flexDirection: 'row', marginBottom: Height * 0.02}}>
          <TouchableOpacity
            style={styles.myClubButton}
            onPress={() => navigation.navigate('ClubMain')}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/DoiT.png')}
            />
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.myClubButton}
            onPress={() => navigation.navigate('ClubMain')}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/Ggong.png')}
            />
          </TouchableOpacity>
        </View>
        <View style={{flexDirection: 'row'}}>
          <TouchableOpacity
            style={styles.myClubButton}
            onPress={() => navigation.navigate('ClubMain')}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/Sweat.png')}
            />
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.myClubButton}
            onPress={() => setModalVisible(true)}>
            <Image
              style={styles.clubImage}
              resizeMode="stretch"
              source={require('../../images/AddClub.png')}
            />
          </TouchableOpacity>
        </View>
      </View>
      <View style={{margin: Width * 0.05, flex: 0.4}}>
        <Text style={styles.fontStyle}>동아리 정보가 궁금하다면?</Text>
        <View style={{flex: 1}}>
          <TouchableOpacity style={styles.otherClubButton}>
            <Text>동아리 이름 1</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.otherClubButton}>
            <Text>동아리 이름 2</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.otherClubButton}>
            <Text>동아리 이름 3</Text>
          </TouchableOpacity>
        </View>
      </View>
      {/* 모달 레이아웃 구성 */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
      >
        <View style={{backgroundColor:(0,0,0,0), width: Width , height : Height}}>
          <View style={{backgroundColor:"white"}}>
            <Text>Hello World!</Text>
            <TouchableOpacity
              style={{backgroundColor:"tomato"}}
              onPress={() => {
                setModalVisible(false)
              }}
            >
              <Text>Hide Modal</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  clubImage: {
    width: Width * 0.44,
    height: Height * 0.2,
    borderRadius: 10,
  },
  myClubButton: {
    elevation: 3,
    borderRadius: 10,
    marginRight: Width * 0.02,
  },
  fontStyle: {
    fontSize: 25,
    marginBottom: Height * 0.01,
  },
  otherClubButton: {
    borderRadius: 10,
    height: Height * 0.08,
    borderWidth: 0.5,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: Height * 0.01,
  },
});

export default Main;
