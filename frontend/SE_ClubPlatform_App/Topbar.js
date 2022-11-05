import React from 'react';
import {StyleSheet, View, Text} from 'react-native';

const TopBar = () => {
    return (
        <View style={styles.header}>
            <Text style={styles.appTitle}>ClubPlatform</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    header : {
      justifyContent : "center",
      height : 50,
      backgroundColor : "#FFA5A5",
    },
    appTitle : {
        justifyContent : 'center',
        left : 16,
        fontSize : 16,
    },
  });


export default TopBar;