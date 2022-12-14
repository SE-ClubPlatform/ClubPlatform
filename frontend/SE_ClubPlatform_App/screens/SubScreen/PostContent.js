import React, {useEffect, useState} from 'react';
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
import userToken from '../../recoils/userToken';
import {useRecoilState, useRecoilValue} from 'recoil';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function PostContent({navigation, route}) {
  const [userToken_R, setUserToken] = useRecoilState(userToken);
  const [comList, setComList] = useState();

  async function getComment(token) {
    try {
      console.log(route.params.ClubId);
      console.log(route.params.PostType);
      console.log(route.params.Postid);
      const response = await axios.get(
        `http://sogong-group3.kro.kr/club/${route.params.ClubId}/${
          route.params.PostType === 'group'
            ? 'community'
            : route.params.PostType
        }/${route.params.Postid}/comments`,
        {
          headers: {
            Authorization: token,
          },
        },
      );
      if (response) {
        console.log(response.data);
        setComList(response.data);
      }
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    getComment(`Bearer ${userToken_R}`);
  }, []);
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
          {comList
            ? comList.map((com, index) => (
                <CommentComponent
                  key={index}
                  name={com.writer}
                  content={com.content}
                />
              ))
            : null}
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
