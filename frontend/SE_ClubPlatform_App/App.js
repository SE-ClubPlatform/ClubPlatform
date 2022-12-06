import React from 'react';
import {SafeAreaView, StyleSheet, Text, View, Image} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import TopBar from './screens/Bar/Topbar';
import BottomBar from './BottomBar';
import {Dimensions} from 'react-native';

import Register from './screens/AuthScreen/Register';
import Login from './screens/AuthScreen/Login';
import Splash from './screens/AuthScreen/Splash';
import Home from './screens/MainScreen/Home';
import Group from './screens/MainScreen/Group';
import Clubroom from './screens/MainScreen/Clubroom';
import News from './screens/MainScreen/News';
import Notice from './screens/MainScreen/Notice';
import Main from './screens/MainScreen/Main';
import AddClub from './screens/SubScreen/AddClub';
import Anonymous from './screens/MainScreen/Anonymous';
import Post from './screens/SubScreen/Post';
import WorkFlow from './screens/MainScreen/WorkFlow';
import MemberList from './screens/MainScreen/MemberList';
import PostContent from './screens/SubScreen/PostContent';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

const Tab = createBottomTabNavigator();
const Stack = createStackNavigator();
const HomeStack = createStackNavigator();
const ClubMainStack = createBottomTabNavigator();

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

const HomeStackScreen = ({navigation}) => {
  return (
    <HomeStack.Navigator
      style={styles.top}
      initialRouteName="Main"
      topBarOptions={{
        labelStyle: {color: '#FFAAB3', fontWeight: '500', fontSize: 11},
      }}>
      <HomeStack.Screen
        name="Main"
        component={Main}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="Post"
        component={Post}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="ClubMain"
        component={ClubMainStackScreen}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="AddClub"
        component={AddClub}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="WorkFlow"
        component={WorkFlow}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="Notice"
        component={Notice}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="Group"
        component={Group}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="MemberList"
        component={MemberList}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
      <HomeStack.Screen
        name="PostContent"
        component={PostContent}
        options={{title: '', headerTransparent: true, headerShown: false}}
      />
    </HomeStack.Navigator>
  );
};

const ClubMainStackScreen = ({navigation, route}) => {
  return (
    <ClubMainStack.Navigator
      style={styles.top}
      initialRouteName="홈"
      screenOptions={{
        tabBarLabelStyle: {
          color: '#5362b2',
          fontWeight: '500',
          fontSize: 11,
        },
      }}>
      <ClubMainStack.Screen
        name="공지사항"
        component={Notice}
        options={{
          headerShown: false,
          tabBarIcon: ({focused}) => {
            return <Image source={require('./icons/Notice.png')} />;
          },
        }}
      />
      <ClubMainStack.Screen
        name="소모임 모집"
        component={Group}
        options={{
          headerShown: false,
          tabBarIcon: ({focused}) => {
            return <Image source={require('./icons/Group.png')} />;
          },
        }}
      />
      <ClubMainStack.Screen
        name="홈"
        component={Home}
        options={{
          headerShown: false,
          tabBarIcon: ({focused}) => {
            return <Image source={require('./icons/Home.png')} />;
          },
        }}
      />

      <ClubMainStack.Screen
        name="동아리방 현황"
        component={Clubroom}
        options={{
          headerShown: false,
          tabBarIcon: ({focused}) => {
            return <Image source={require('./icons/Clubroom.png')} />;
          },
        }}
      />
      <ClubMainStack.Screen
        name="익명 신문고"
        component={Anonymous}
        options={{
          headerShown: false,
          tabBarIcon: ({focused}) => {
            return <Image source={require('./icons/News.png')} />;
          },
        }}
      />
    </ClubMainStack.Navigator>
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
        {/* <Stack.Screen
          name="ClubMain"
          component={ClubMainStackScreen}
          options={{headerShown: false}}
        /> */}
        <Stack.Screen
          name="HomeStack"
          component={HomeStackScreen}
          options={{headerShown: false}}
        />
        <Stack.Screen
          name="AddClub"
          component={AddClub}
          options={{headerShown: false}}
        />
        <Stack.Screen
          name="WorkFlow"
          component={WorkFlow}
          options={{headerShown: false}}
        />
        {/* <Stack.Screen
          name="MemberList"
          component={MemberList}
          options={{headerShown: false}}
        /> */}
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
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
  input: {
    width: '200px',
    marginBottom: '25px',
  },
  top: {
    backgroundColor: '#FFAAB3',
  },
});

export default App;
