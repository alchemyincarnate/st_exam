import React, {
    Component,
  } from 'react';
import {
  StyleSheet,
  View,
  Text,
  Alert,
} from 'react-native';

// In-project classes
import {LocalizationLoader} from './src/components/LocalizationLoader.js'
import {LocalizedText} from './src/components/LocalizedText.js'
import {LocalizeButton} from './src/components/LocalizeButton.js'

type Props = {};
export default class App extends Component<Props> {


  constructor ( props )
  {
    super( props );
    this.state = {
      reference: null,
      language: "en"
    }
  }

  render ()
  {
    return (
      // Currently non-functional
      // <NativeLayoutView>
      // </NativeLayoutView>
      // TODO: This list of LocalizedText and LocalizeButton need to be replaced by the layout.xml

      <View style={styles.container}>
        <LocalizedText
          reference={this.state.reference}
          language={this.state.language}
          locKey='HELLO_WORLD'
          isShowSource='true'
        />
        <LocalizeButton
          reference={this.state.reference}
          language="en"
          onSetLang={this.setLang}
        />
        <LocalizeButton
          reference={this.state.reference}
          language="hi"
          onSetLang={this.setLang}
        />
        <LocalizeButton
          reference={this.state.reference}
          language="ja"
          onSetLang={this.setLang}
        />
        <LocalizationLoader
          url={'https://jsonblob.com/api/jsonBlob/2f378791-6c60-11ea-9610-89cf4e8bc64f'}
          onLoad={this.onLoadLocalization}
        />
      </View>
    );
  }

  onLoadLocalization = ( locFile ) =>
  {
    this.setState( {
      reference: locFile
    });
  }

  setLang = ( newLang ) =>
  {
    this.setState( {
      language: newLang
    });
  }
}


const styles = StyleSheet.create(
  {
    container: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
      textAlign: 'center',
      backgroundColor: '#F5FCFF'
    },
  }
)