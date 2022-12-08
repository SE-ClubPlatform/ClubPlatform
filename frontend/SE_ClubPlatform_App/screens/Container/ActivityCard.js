import React, { useEffect, useState } from 'react'
import {
    View,
    Text,
    StyleSheet,
    Dimensions,
    Image,
    TouchableOpacity,
    TextInput,
    Modal,
} from 'react-native';
import { RadioButton } from 'react-native-paper';
import * as Progress from 'react-native-progress';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function ActivityCard() {
    const [voteVisible, setVoteVisible] = useState(false)
    const [checked, setChecked] = useState('first')
    return(
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
            <Text>동아리 활동에 대한 개괄적인 설명이 기재되는 칸입니다. 동아리 활동에 대한 개괄적인 설명이 기재되는 칸입니다. </Text>
        </View>
        <View style={styles.container_sub}>
        <TouchableOpacity
        onPress={()=>{setVoteVisible(true)}}>
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
        width={Width*0.8} 
        height={Height*0.012} 
        borderRadius={10}
        color={"#B2AC8A"}
        />
        <Modal animationType="fade" transparent={true} visible={voteVisible}>
        <View
          style={{
            backgroundColor: 'rgba(60, 60, 60, 0.5)',
            width: Width,
            height: Height,
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <View
            style={{
              backgroundColor: 'white',
              width: Width * 0.7,
              borderRadius: 10,
              padding: 15,
            }}>
            <View
              style={{
                flexDirection: 'row',
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                marginBottom : 10,
              }}>
              <Text style={{fontSize: 20, flex: 1}}>투표하기</Text>
              <TouchableOpacity
                onPress={() => {
                    setVoteVisible(false);
                }}>
                <Image
                  source={require('../../icons/ic_close.png')}
                  resizeMode="contain"
                  style={{
                    flex: 1,
                    height: Height * 0.05,
                    width: Height * 0.05,
                  }}
                />
              </TouchableOpacity>
            </View>
            <View
              style={{
                flexDirection: 'row',
                alignItems: 'center',
                alignContent: 'space-between',
                justifyContent: 'center',
              }}>
              <RadioButton
                value="part"
                status={ checked === 'part' ? 'checked' : 'unchecked' }
                onPress={() => setChecked('part')}
                />
              <Text style={{flex:1,}}>참가</Text>
            </View>
            <View
              style={{
                flexDirection: 'row',
                alignItems: 'center',
                alignContent: 'space-between',
                justifyContent: 'center',
              }}>
              <RadioButton
                value="unpart"
                status={ checked === 'unpart' ? 'checked' : 'unchecked' }
                onPress={() => setChecked('unpart')}
                />
              <Text style={{flex:1,}}>불참</Text>
            </View>
            <TouchableOpacity style={styles.btn_gray}
            onPress={()=> {
                console.log(checked)
                setVoteVisible(false)
                }}>
              <Text style={{textAlign:"center"}}>완료</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
    );
}
const styles = StyleSheet.create({
    btn_gray : {
      paddingHorizontal : 30,
      paddingVertical : 10,
      backgroundColor : "#D9D9D9",
      borderRadius : 10,
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
      alignItems : "center",
      marginBottom: 20,
    },
    container_sub: {
      flexDirection: 'row',
      alignItems: 'center',
      justifyContent : 'space-between',
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

export default  ActivityCard;