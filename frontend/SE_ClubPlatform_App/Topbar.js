import React from 'react';
import {StyleSheet, View, Text, TouchableOpacity} from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';

const TopBar = () => {
    return (
        <View style={styles.header}>
            <Text style={styles.appTitle}>Club Platform</Text>
            <TouchableOpacity styel={styles.alarm}>
                <Icon name="bells" size={25} color="#FFF" />
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    header : {
      height : 50,
      backgroundColor : "#FFA5A5",
      alignItems : 'center',
      flexDirection : "row"
    },
    appTitle : {
        flex : 4,
        justifyContent : 'center',
        textAlign : 'center',
        fontSize : 16,
        color : "#FFF",
        fontWeight : 'bold',
    },
    alarm : {
        flex : 1,
        justifyContent : 'center',
        alignItems : 'center',
        right : 5,
        backgroundColor : "cyan"
    }
  });


export default TopBar;