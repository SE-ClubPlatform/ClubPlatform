/* eslint-disable react-native/no-inline-styles */
import React, {useState} from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  Dimensions,
} from 'react-native';

const Height = Dimensions.get('window').height;
const Width = Dimensions.get('window').width;

function TagComponent({tag}) {
  return (
    <TouchableOpacity style={styles.TagButton}>
      <Text style={{color: '#4f4f4f'}}>{tag}</Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  TagButton: {
    width: (Width - 80) / 2,
    height: Height * 0.05,
    borderRadius: 8,
    marginHorizontal: 10,
    justifyContent: 'center',
    alignItems: 'center',
    flexDirection: 'row',
    backgroundColor: '#d9d9d9',
  },
});

export default TagComponent;
