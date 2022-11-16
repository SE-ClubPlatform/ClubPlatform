import React from 'react';
import {View, Text, Button, ScrollView, StyleSheet, TextInput} from 'react-native';
import { back } from 'react-native/Libraries/Animated/Easing';
import Topbar from '../Bar/Topbar';

function Home({navigation}) {
  return (
    <View>
      <Topbar />
      <ScrollView>
        <View style={styles.card}>
          <View style={styles.container}>
            <Text style={styles.clubTitle}>Do IT!</Text>
            <View style={styles.container_right}>
              <View style={styles.gray_card}>
                <Text style={styles.captain}>회장</Text>
                <Text style={styles.captain_name}>김두잇</Text>
              </View>
              <View style={styles.gray_card}>
                <Text style={styles.captain}>부원</Text>
                <Text style={styles.captain_name}>00명</Text>
              </View>
            </View>
          </View>
          <TextInput style={styles.input} placeholder="Add an item!" />
        </View>
        <View style={styles.card}>
          <TextInput style={styles.input} placeholder="Add an item!" />
        </View>
        <View style={styles.card}>
          <TextInput style={styles.input} placeholder="Add an item!" />
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container :{
    flexDirection:"row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  container_right :{
    flex : 2,
    flexDirection:"row",
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
    justifyContent: "center",
    alignItems: "center"
    
  },
  clubTitle : {
    flex : 1,
    alignContent :"center",
    textAlign :
  },
  captain : {
    flex : 1,
  },
  captain_name : {
    flex : 1,
  },
})

export default Home;
