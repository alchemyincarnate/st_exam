import React, {Component} from 'react';
import {NativeModules, View, Text, Button, TouchableOpacity} from 'react-native';

import { useNavigation } from '@react-navigation/native';
import { WebView } from 'react-native-webview';

var JavaRssReader = NativeModules.JavaRssReader;

/*
* Converts a single view into a touchable that opens the Entry page
*/
function OpenLink ({link}) {
    const navigation = useNavigation();
    return (
        <TouchableOpacity
            onPress={ () => navigation.navigate('Entry', {url: link})}
            style={styles.flex}
            >
        </TouchableOpacity>
    );
}

/*
* This is a representation of a single entry in RSS
* Currently only shows Title and html content
*/
export class RssEntry extends Component {

    constructor( props )
    {
        super( props );
        this.state = {
            summary: ""
        }
    }

    componentDidMount( ) {
        this.getEntrySummary( );
    }

    componentDidUpdate (previousProps, previousState) {
        if( previousProps.entryId !== this.props.entryId )
            this.getEntrySummary( );
        // this.loadRssFeed('https://www.polygon.com/rss/index.xml');
        // this.loadRssFeed('https://storage.googleapis.com/singtel-test/polygon.xml');
    }

    render() 
    {
        console.log( this.state.summary.content );
        return (
            <View style={styles.container}> 
                <Text style={styles.title}>
                    {this.state.summary.title}
                </Text>
                <WebView
                    style={styles.webView}
                    originWhitelist={['*']}
                    source={{html: this.state.summary.content}}
                />
                <OpenLink link={this.state.summary.link}/>
            </View>
        );
    }

    open () {
    }


    // Gets an rss entry from ModuleB
    getEntrySummary( ) {
        JavaRssReader.getEntrySummary (
            this.props.documentId,
            this.props.entryId,
            (err) => {
                console.error( err );
            },
            (result) => {
                this.setState( {
                    summary: result
                });
            }
        )
    }
}


import { StyleSheet } from 'react-native'
const styles = StyleSheet.create(
    {
        container: {
            width: '92%',
            height: 150,
            margin: 12
        },
        flex: {
            position: 'absolute',
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
            justifyContent: 'center',
            alignItems: 'center',
        },
        title: {
            padding: 0,
            fontWeight: 'bold',
            textShadowOffset: {width:4, height:4}
        },
        webView: {
            width: '100%'
        }
    }
)