{index === 1 || index === 2 ? (
  <View style={styles.apply_text_container}>
    <Text style={{borderWidth: 1}}>{data.context}</Text>
  </View>
) : (
  <View style={styles.apply_text_container}>
    <Text style={{borderWidth: 1}}>{data.context}</Text>
  </View>
)}{flexDirection: index === 1 ? 'column' : 'row'},



{Sample.map((data, index) => (
  <View style={styles.text_box}>
    <View style={{flexDirection: 'row'}}>
      <View style={styles.apply_text_container}>
        <Text style={styles.apply_attribute_text}>{data.attribute}</Text>
      </View>
      <View style={styles.apply_text_container}>
        <Text style={styles.apply_context_text}>{data.context}</Text>
      </View>
    </View>
  </View>
))}

<View style={styles.test}>
        <Text>gi</Text>

      <View style={[styles.text_box, {flexDirection: 'row'}]}>
