import React, {useState} from 'react';
import {View, Text, StyleSheet, Image, Dimensions} from 'react-native';
import {TouchableOpacity} from 'react-native-gesture-handler';
import {useNavigation} from '@react-navigation/native';
import {back} from 'react-native/Libraries/Animated/Easing';
import {removeOrientationListener} from 'react-native-responsive-screen';

function Home_Contents(Props) {
  const navigation = useNavigation();
  return (
    <View>
      <View style={styles.card}>
        <View style={styles.container_title}>
          <Text style={styles.cardTitle}>{Props.title}</Text>
          <TouchableOpacity
            activeOpacity={0.8}
            onPress={() => {
              navigation.navigate(Props.location);
            }}
            style={{
              flex: 1,
              flexDirection: 'row',
              justifyContent: 'space-between',
              alignItems: 'center',
            }}>
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
            <Text>{Props.title1}</Text>
          </View>
        </View>
        <View style={styles.container_sub}>
          <View style={{marginRight: 15}}>
            <Text>2</Text>
          </View>
          <View>
            <Text>{Props.title2}</Text>
          </View>
        </View>
        <View style={styles.container_sub}>
          <View style={{marginRight: 15}}>
            <Text>3</Text>
          </View>
          <View>
            <Text>{Props.title3}</Text>
          </View>
        </View>
        <View style={styles.container_sub}>
          <View style={{marginRight: 15}}>
            <Text>4</Text>
          </View>
          <View>
            <Text>{Props.title4}</Text>
          </View>
        </View>
        <View style={styles.container_sub}>
          <View style={{marginRight: 15}}>
            <Text>5</Text>
          </View>
          <View>
            <Text>{Props.title5}</Text>
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
