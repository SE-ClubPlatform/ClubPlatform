import React from 'react';
import {
  View,
  Text,
  Button,
  ScrollView,
  TouchableOpacity,
  StyleSheet,
  Image,
} from 'react-native';
import {Dimensions} from 'react-native';
import Topbar from '../Bar/Topbar';
import PostComponent from './PostComponent';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

const postData = [
  {
    title: '동아리방 사용 관련 공지사항',
    author: 'GM우현',
    date: '2022/11/04',
    time: '09:15',
    commentCount: 5,
  },
  {
    title: 'MT 관련 공지사항',
    author: 'GM준수',
    date: '2022/10/25',
    time: '11:10',
    commentCount: 10,
  },
  {
    title: '대여사업 관련 공지사항',
    author: 'GM지영',
    date: '2022/10/11',
    time: '16:17',
    commentCount: 3,
  },
  {
    title: '개강총회 관련 공지사항',
    author: 'GM우진',
    date: '2022/10/09',
    time: '12:11',
    commentCount: 14,
  },
  {
    title: '정기모임 관련 공지사항',
    author: 'GM상훈',
    date: '2022/07/21',
    time: '10:12',
    commentCount: 10,
  },
];

const postData2 = [
  {
    title: '텔레토비 동산 피크닉 갈 사람 구합니다',
    author: '김채원',
    date: '2022/12/02',
    time: '19:15',
    commentCount: 6,
    isFinish: false,
  },
  {
    title: '아웃닭 파티원 구해요',
    author: '장원영',
    date: '2022/11/25',
    time: '11:10',
    commentCount: 10,
    isFinish: false,
  },
  {
    title: '저녁식사 팟 구함',
    author: '백종원',
    date: '2022/10/10',
    time: '16:17',
    commentCount: 3,
    isFinish: false,
  },
  {
    title: '동아리 박람회 구경갈 사람 구해요',
    author: '박민수',
    date: '2022/10/01',
    time: '14:11',
    commentCount: 14,
    isFinish: true,
  },
  {
    title: '벚꽃 보러갈 멤버 구함',
    author: '임준철',
    date: '2022/09/21',
    time: '17:12',
    commentCount: 8,
    isFinish: false,
  },
  {
    title: '피시방 롤 내전 팟 구합니당',
    author: '차은우',
    date: '2022/08/22',
    time: '08:12',
    commentCount: 20,
    isFinish: true,
  },
];

const postData3 = [
  {
    title: '동아리방 청결 문제에 대해 건의드립니다.',
    author: '최연우',
    date: '2022/12/01',
    time: '15:15',
    commentCount: 2,
    isFinish: false,
  },
  {
    title: '정기모임 활동에 관련하여 질문드립니다.',
    author: '이연지',
    date: '2022/11/05',
    time: '10:11',
    commentCount: 7,
    isFinish: false,
  },
  {
    title: '히터 사용 관련하여 건의드립니다.',
    author: '김민수',
    date: '2022/10/25',
    time: '08:51',
    commentCount: 10,
    isFinish: false,
  },
];

function Board({navigation, club_id, boardType}) {
  function Title() {
    if (boardType === 'anonymous') {
      return (
        <View>
          <Text style={styles.fontStyle}>익명 신문고 🥁</Text>
        </View>
      );
    } else if (boardType === 'notice') {
      return (
        <View>
          <Text style={styles.fontStyle}>공지사항 📢</Text>
        </View>
      );
    } else if (boardType === 'group') {
      return (
        <View>
          <Text style={styles.fontStyle}>소모임 모집 👥</Text>
        </View>
      );
    }
  }

  const postList = postData.map(post => (
    <PostComponent
      postType={boardType}
      title={post.title}
      author={post.author}
      date={post.date}
      time={post.time}
      commentCount={post.commentCount}
      isFinish={post.isFinish}></PostComponent>
  ));

  const postList2 = postData2.map(post => (
    <PostComponent
      postType={boardType}
      title={post.title}
      author={post.author}
      date={post.date}
      time={post.time}
      commentCount={post.commentCount}
      isFinish={post.isFinish}></PostComponent>
  ));

  const postList3 = postData3.map(post => (
    <PostComponent
      postType={boardType}
      title={post.title}
      author={post.author}
      date={post.date}
      time={post.time}
      commentCount={post.commentCount}
      isFinish={post.isFinish}></PostComponent>
  ));

  // function postListComponent() {
  //   if (boardType === 'notice') {
  //     return <View>{postList}</View>;
  //   } else if (boardType === 'group') {
  //     return <View>{postList2}</View>;
  //   } else {
  //     return <View>{postList3}</View>;
  //   }
  // }

  return (
    <View style={{flex: 1, backgroundColor: 'white'}}>
      <Topbar navigation={navigation} />
      <View
        style={{
          flex: 1,
          marginHorizontal: Width * 0.03,
          marginTop: Width * 0.05,
        }}>
        <View
          style={{
            flexDirection: 'row',
            justifyContent: 'space-between',
            paddingRight: Width * 0.02,
            marginLeft: Width * 0.02,
          }}>
          <Title />
        </View>
        <View style={{flex: 1}}>
          <ScrollView style={{flex: 0.9}}>
            {boardType === 'notice'
              ? postList
              : boardType === 'group'
              ? postList2
              : postList3}
          </ScrollView>
          <View
            style={{
              flex: 0.1,
              alignItems: 'center',
              marginTop: Height * 0.01,
            }}>
            <TouchableOpacity
              style={{
                backgroundColor: '#E7E7E7',
                width: Width * 0.3,
                height: Height * 0.055,
                justifyContent: 'center',
                alignItems: 'center',
                borderRadius: 8,
                flexDirection: 'row',
              }}
              onPress={() => navigation.navigate('Post')}>
              <Image
                style={{
                  width: Width * 0.05,
                  height: Width * 0.05,
                  resizeMode: 'contain',
                  marginRight: Width * 0.015,
                }}
                source={require('../../icons/Write.png')}
              />
              <Text style={{color: 'black'}}>작성하기</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  postStyle: {
    borderRadius: 10,
    height: Height * 0.1,
    backgroundColor: '#FFFFFF',
    elevation: 3,
    margin: 7,
    marginVertical: Height * 0.005,
  },
  fontStyle: {
    fontSize: 28,
    marginBottom: Height * 0.03,
  },
  postButton: {
    width: Width * 0.18,
    height: Height * 0.05,
    borderWidth: 0.3,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: Height * 0.007,
    marginLeft: Width * 0.02,
  },
});

export default Board;
