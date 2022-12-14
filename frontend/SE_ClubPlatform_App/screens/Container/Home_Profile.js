import React from 'react';
import {
  View,
  Text,
  Button,
  ScrollView,
  StyleSheet,
  TextInput,
  Image,
  Dimensions,
} from 'react-native';
import {TouchableOpacity} from 'react-native-gesture-handler';
import {back} from 'react-native/Libraries/Animated/Easing';
import Topbar from '../Bar/Topbar';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function Home_Profile({navigation, clubInfo}) {
  console.log(clubInfo);
  return (
    <View>
      <View style={styles.card}>
        <View style={styles.container}>
          <Text style={styles.cardTitle}>
            {clubInfo ? clubInfo.clubName : null}
          </Text>
          <View style={styles.container_right}>
            <View style={styles.gray_card}>
              <View>
                <Text style={styles.gray_card_title}>회장</Text>
              </View>
              <View>
                <Text style={styles.gray_card_content}>
                  {clubInfo ? clubInfo.presidentName : null}
                </Text>
              </View>
            </View>

            <TouchableOpacity
              style={styles.gray_card}
              onPress={() => navigation.navigate('MemberList')}>
              <View>
                <Text style={styles.gray_card_title}>부원</Text>
              </View>
              <View>
                <Text style={styles.gray_card_content}>
                  {clubInfo ? clubInfo.memberCounts : null}명
                </Text>
              </View>
            </TouchableOpacity>
          </View>
        </View>
        <View style={styles.container}>
          <Image
            style={styles.clubImg}
            resizeMode="stretch"
            source={{
              uri: clubInfo ? 'data:image/png;base64,' + clubInfo.image : null,
            }}
          />
          <View style={{flex: 1}}>
            <Text style={styles.captain_name}>
              {clubInfo ? clubInfo.introduce : null}
            </Text>
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
    width: 110,
    height: 110,
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
    alignSelf: 'center',
    padding: 5,
    justifyContent: 'space-between',
  },
  cardTitle: {
    // flex: 1,
    width: Width * 0.28,
    // alignContent: 'center',
    paddingLeft: Width * 0.06,
    fontSize: 20,
    fontWeight: 'bold',
    justifyContent: 'center',
    alignItems: 'center',
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
export default Home_Profile;
