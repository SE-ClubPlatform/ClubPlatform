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

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function PostContent({navigation, route}) {
  const [name, setName] = useState('GM우현');
  const [date, setDate] = useState('2022/11/04');
  const [time, setTime] = useState('09:15');
  const [title, setTitle] = useState('동아리방 사용 관련 공지사항');
  const [content, setContent] = useState(
    ' 안녕하세요 아주대학교 Do-iT 동아리 입니다. 동아리방 사용에 관한 공지사항 내용입니다 읽어주셔서 감사합니다 :) ',
  );

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
            {/* <Image
              style={{
                width: Width * 0.9,
                height: Width * 0.9,
                resizeMode: 'stretch',
                marginBottom: Height * 0.01,
              }}
              source={require('../../images/notice.png')}
            /> */}
            <Text style={{fontSize: 16}}>{route.params.Content}</Text>
          </View>
        </View>
        <View style={styles.commentArea}>
          <View
            style={{
              marginVertical: Height * 0.01,
              backgroundColor: '#f2f2f2',
              borderRadius: 10,
              padding: 10,
            }}>
            <View
              style={{
                flexDirection: 'row',
                alignItems: 'center',
                marginBottom: Height * 0.01,
              }}>
              <Image
                style={{
                  width: Width * 0.07,
                  height: Width * 0.07,
                  resizeMode: 'stretch',
                  marginRight: Width * 0.01,
                }}
                source={require('../../icons/User.png')}
              />
              <Text>GM지영</Text>
            </View>
            <View>
              <Text>넵 확인하였습니다 !</Text>
              <Text style={{fontSize: 14}}>11/05 18:53</Text>
            </View>
          </View>
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
