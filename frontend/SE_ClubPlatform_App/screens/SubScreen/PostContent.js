import React, {useState} from 'react';
import {
  View,
  Text,
  Button,
  Dimensions,
  StyleSheet,
  ScrollView,
  Image,
} from 'react-native';
import Topbar from '../Bar/Topbar';
import CommentComponent from './CommentComponent';
import axios from 'axios';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function PostContent({navigation, route}) {
  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <ScrollView
        style={{
          flex: 1,
          paddingVertical: Height * 0.01,
          paddingHorizontal: Width * 0.03,
        }}>
        <View style={styles.contentArea}>
          <View style={styles.postTop}>
            <Image
              style={{marginRight: Width * 0.01}}
              source={require('../../icons/User.png')}
            />
            <View>
              <Text style={{fontWeight: 'bold'}}>
                {route.params.PostType === 'anonymous'
                  ? '익명'
                  : route.params.Author}
              </Text>
              <Text>{route.params.Date}</Text>
            </View>
          </View>
          <View style={styles.postBottom}>
            <Text
              style={{
                fontWeight: 'bold',
                fontSize: 20,
                fontFamily: 'NanumSquareNeoTTF-bRg',
                marginBottom: Height * 0.02,
              }}>
              {route.params.Title}
            </Text>
            <Text style={{fontSize: 16}}>{route.params.Content}</Text>
          </View>
        </View>
        <View style={styles.commentArea}>
          <CommentComponent />
        </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  contentArea: {borderBottomWidth: 0.2, paddingBottom: Height * 0.02},
  commentArea: {},
  postTop: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: Height * 0.02,
  },
  postBottom: {},
});

export default PostContent;
