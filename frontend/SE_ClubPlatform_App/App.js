import React from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import TopBar from './screens/Bar/Topbar';
import BottomBar from './BottomBar';

import Register from './screens/AuthScreen/Register';
import Login from './screens/AuthScreen/Login';
import Splash from './screens/AuthScreen/Splash';

const Tab = createBottomTabNavigator(); // 탭 네비게이터 선언
const Stack = createStackNavigator(); // 인증 페이지 스택 선언
const ClubMainStack = createStackNavigator(); // 메인 페이지 스택 선언

// Stack Navigator for Login and Register and Logout Screen
const Auth = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="Login"
        component={Login}
        options={{title: '', headerTransparent: true}}
      />
      <Stack.Screen
        name="Register"
        component={Register}
        options={{title: '', headerTransparent: true}}
      />
    </Stack.Navigator>
  );
};

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        <Stack.Screen
          name="Splash"
          component={Splash}
          options={{headerShown: false}}
        />
        <Stack.Screen
          name="Auth"
          component={Auth}
          options={{headerShown: false}}
        />
        {/* <Stack.Screen name="Main" component={Main} options={{headerShown:false}}/> */}
      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    flexDirection: 'column',
  },
  header: {
    flex: 1,
  },
  content: {
    flex: 1,
    backgroundColor: '#ABABAB',
  },
  appTitle: {
    color: '#FFF',
    fontSize: 36,
    fontWeight: '300',
    textAlign: 'center',
    backgroundColor: '#3143e8',
  },
  footer: {
    flex: 1,
  },
});

export default App;
