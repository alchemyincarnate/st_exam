import React, {Component} from 'react';
import {NativeModules, ScrollView, Text, ActivityIndicator} from 'react-native';

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { WebView } from 'react-native-webview';

import { RssFeedView } from './RssFeedView.js';

const Stack = createStackNavigator();

function ActivityIndicatorLoadingView() {
    return (
        <ActivityIndicator
                color="#009688"
                size="large"
                style={styles.ActivityIndicatorStyle}
        />
    );
}

export class RssFeedViewer extends Component {

    constructor( props )
    {
        super( props );
        this.state = { visible: true };
    }

    getRssListView ({route, navigation}) {
        return (
            <ScrollView>
                <RssFeedView url='https://www.polygon.com/rss/index.xml' title='Main article'/>
                <RssFeedView url='https://storage.googleapis.com/singtel-test/polygon.xml' title='Recommended article'/>
            </ScrollView>
        );
    }
    
    getRssEntryView ({route, navigation}) {
        
        let visible = true;
        return (
            <WebView
                style={styles.WebViewStyle}
                source={{uri: route.params.url}}
                renderLoading={() => {
                    return <ActivityIndicator style={styles.ActivityIndicatorStyle} size="large"/>
                }}
            />
        );
    }

    render() 
    {
        return (
            <NavigationContainer>
                <Stack.Navigator initialRouteName="Home">
                    <Stack.Screen name="Home" component={this.getRssListView} />
                    <Stack.Screen name="Entry" component={this.getRssEntryView} />
                </Stack.Navigator>
            </NavigationContainer>
        );
    }
}



import { StyleSheet } from 'react-native'
const styles = StyleSheet.create(
    {
        WebViewStyle: {
            justifyContent: 'center',
            alignItems: 'center',
            flex: 1,
            marginTop: 40
        },
        ActivityIndicatorStyle: {
            position: 'absolute',
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
            justifyContent: 'center',
            alignItems: 'center',
            backgroundColor: 'rgba(0,0,0, 0.3)'
        },
        title: {
            padding: 0,
            fontWeight: 'bold',
            textShadowOffset: {width:4, height:4}
        }
    }
)