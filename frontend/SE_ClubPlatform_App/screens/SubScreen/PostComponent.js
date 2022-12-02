import React, {useState} from 'react';
import {View, Text, Button, StyleSheet, Dimensions, Image} from 'react-native';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function PostComponent({
  navigation,
  postType,
  title,
  author,
  date,
  time,
  commentCount,
  isFinish,
}) {
  // const [boardType, setBoardType] = useState('공지사항');
  // const [name, setName] = useState('GM우현');
  // const [date, setDate] = useState('2022/11/04');
  // const [time, setTime] = useState('09:15');
  // const [content, setContent] = useState('동아리방 사용 관련 공지사항');
  // const [commentCount, setCommentCount] = useState(5);
  // const [isWorkFlow, setIsWorkFlow] = useState(true);
  // const [isRecruit, setIsRecruit] = useState(false);

  return (
    <View style={styles.postStyle}>
      <View style={styles.helfArea}>
        <View style={{flexDirection: 'row', alignItems: 'center'}}>
          <Image
            style={styles.userIcon}
            source={require('../../icons/User.png')}
            resizeMode="contain"
          />
          <Text style={{fontSize: 15, fontWeight: 'bold'}}>
            {postType === 'anonymous' ? '익명' : author}
          </Text>
          {postType === 'group' ? (
            <View style={styles.recruitIcon}>
              <Text
                style={{
                  fontSize: 12,
                  color: 'white',
                }}>
                {isFinish ? '모집완료' : '모집중'}
              </Text>
            </View>
          ) : null}
        </View>
        <View style={{alignItems: 'flex-end'}}>
          <Text style={styles.dateStyle}>{date}</Text>
          <Text style={styles.dateStyle}>{time}</Text>
        </View>
      </View>
      <View style={styles.helfArea}>
        <View
          style={{
            flexDirection: 'row',
            justifyContent: 'space-between',
            alignItems: 'center',
            flex: 1,
            paddingTop: Height * 0.01,
          }}>
          <Text style={{}}>{title}</Text>
          <View style={{flexDirection: 'row'}}>
            {/* <Image
              style={styles.bottomIcon}
              source={isWorkFlow ? require('../../icons/Workflow.png') : null}
              resizeMode="contain"
            /> */}
            <View style={{flexDirection: 'row', alignItems: 'center'}}>
              <Image
                style={styles.bottomIcon}
                source={require('../../icons/Comment.png')}
                resizeMode="contain"
              />
              <Text style={{color: '#7181c4'}}>{commentCount}</Text>
            </View>
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
    paddingHorizontal: Width * 0.02,
    paddingVertical: Height * 0.01,
  },
  helfArea: {
    flex: 0.5,
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  userIcon: {
    width: Width * 0.055,
    height: Height * 0.055,
    marginRight: Width * 0.01,
  },
  bottomIcon: {
    width: Width * 0.045,
    height: Height * 0.045,
    marginRight: Width * 0.01,
  },
  dateStyle: {
    fontSize: 13,
  },
  recruitIcon: {
    backgroundColor: '#7181c4',
    borderRadius: 15,
    width: Width * 0.15,
    height: Height * 0.026,
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: Width * 0.01,
  },
});

export default PostComponent;
