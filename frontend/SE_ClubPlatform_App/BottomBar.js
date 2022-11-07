import React from 'react';
import {StyleSheet, View, Text, TouchableOpacity} from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';

const BottomBar = () => {
    return (
        <View style={styles.footer}>
            <TouchableOpacity style={styles.item}>
                <Icon name="notification" size={30} color="#FFF" />
                <Text style={styles.font}>공지사항</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.item}>
                <Icon name="wechat" size={30} color="#FFF" />
                <Text style={styles.font}>소모임 모집</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.item}>
                <Icon name="home" size={30} color="#FFF" />
                <Text style={styles.font}>홈</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.item}>
                <Icon name="unlock" size={30} color="#FFF" />
                <Text style={styles.font}>동방 출입</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.item}>
                <Icon name="unknowfile1" size={28} color="#FFF" />
                <Text style={styles.font}>익명 신문고</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    footer : {
        justifyContent : "center",
        bottom:0,
        height:60,
        backgroundColor : "#FFA5A5",
        flexDirection : "row",
      },
    item: {
        flex : 1,
        backgroundColor : "#FFA5A5",
        justifyContent : "center",
        alignItems : "center",
    },
    font : {
        margin : 3,
        fontSize : 12,
        color : "#FFF",
        fontWeight : 'bold'
    }
  });


export default BottomBar;