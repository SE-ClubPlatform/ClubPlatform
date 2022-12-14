import React, {useState} from 'react';
import {
  View,
  Text,
  Button,
  Image,
  StyleSheet,
  Modal,
  TextInput,
  TouchableOpacity,
} from 'react-native';

function AddClubCard() {
  return (
    <View style={styles.card}>
      <View
        style={{
          flexDirection: 'row',
          justifyContent: 'space-between',
          marginBottom: 10,
          alignItems: 'center',
        }}>
        <View
          style={{
            flexDirection: 'row',
            alignItems: 'center',
          }}>
          <Image
            style={styles.clubImg}
            resizeMode="stretch"
            source={require('../../images/Sweat.png')}
          />
          <Text style={{fontSize: 20, fontWeight: '500'}}>SWeat</Text>
        </View>
        <TouchableOpacity>
          <View style={styles.gray_card}>
            <Text>신청하기</Text>
          </View>
        </TouchableOpacity>
      </View>
      <Text>동아리 소개 글 ...</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#fbfbfb',
    borderRadius: 10,
    marginBottom: 10,
    marginTop: 10,
    padding: 20,
    elevation: 5,
    flexDirection: 'column',
  },
  clubImg: {
    width: 40,
    height: 40,
    borderRadius: 10,
    marginRight: 10,
  },
  gray_card: {
    backgroundColor: '#D9D9D9',
    flex: 1,
    borderRadius: 7,
    alignItems: 'center',
    justifyContent: 'center',
    marginVertical: 5,
    padding: 5,
  },
});

export default AddClubCard;
