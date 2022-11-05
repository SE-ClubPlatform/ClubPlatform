import React from 'react';
import {StyleSheet, View, Text} from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';

const BottomBar = () => {
    return (
        <View style={styles.footer}>
            <View style={styles.item1}>
            <Icon name="notification" size={30} color="#900" />
            </View>
            <View style={styles.item2}></View>
            <View style={styles.item3}></View>
            <View style={styles.item4}></View>
            <View style={styles.item5}></View>
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
    item1 : {
        flex : 1,
        backgroundColor : "cyan"
    },
    item2 : {
        flex : 1,
        backgroundColor : "#BBBBBB"
    },
    item3 : {
        flex : 1,
        backgroundColor : "tomato"
    },
    item4 : {
        flex : 1,
        backgroundColor : "#CCCCCC"
    },
    item5 : {
        flex : 1,
        backgroundColor : "#DDDDDD"
    }
  });


export default BottomBar;