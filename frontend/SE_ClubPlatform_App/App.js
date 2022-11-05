import React from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import Topbar from './Topbar';
import BottomBar from './BottomBar';

const App = () => {
  return (
    <SafeAreaView style={styles.container}>
      <Topbar style={styles.header}/>
      <View style={styles.content}>
        {/* <Text style={styles.appTitle}>Hello Todolist</Text> */}
      </View>
      <BottomBar style={styles.footer}/>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container : {
    flex : 1,
    backgroundColor : '#FAFAFA',
    flexDirection : "column",
  },
  header : {
    flex : 1
  },
  content : {
    flex : 1,
    backgroundColor : "#ABABAB"
  },
  appTitle : {
    color : '#FFF',
    fontSize : 36,
    fontWeight : '300',
    textAlign : 'center',
    backgroundColor : '#3143e8',
  },
  footer : {
    flex : 1
  },
});

export default App;