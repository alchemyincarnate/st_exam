/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {RssFeedViewer} from './app01/src/components/RssFeedViewer.js'

type Props = {};
export default class App extends Component<Props> {
  
  constructor( props ) {
    super( props );
  }

  render ()
  {
    return (
      <RssFeedViewer>
      </RssFeedViewer>
    );
  }
}
