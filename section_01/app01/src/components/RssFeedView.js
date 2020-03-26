import React, {Component} from 'react';
import {NativeModules, View, Text, ScrollView, TouchableOpacity} from 'react-native';

import { WebView } from 'react-native-webview';

var JavaRssReader = NativeModules.JavaRssReader;

import {RssEntry} from './RssEntry.js'

/*
* This class represents a set of RssEntries
* They are rendered in a ScrollView
*/
export class RssFeedView extends Component {

    constructor( props )
    {
        super( props );
        this.state = {
            documentId: "",
            entries: []
        }
    }

    componentDidMount () {
        this.loadRssFeed( this.props.url );
    }

    render() 
    {
        let mapped = this.state.entries.map( (prop, key) =>
              <RssEntry key={key} documentId={this.state.documentId} entryId={prop}></RssEntry>
        );

        return (
            <ScrollView>
                <Text style={styles.title}>
                    {this.props.title}
                </Text>
                {mapped}
            </ScrollView>
        );
    }

    // This method loads an rss xml file from the given url
    // Returns the title of the rss feed and this is used as a parameter for getEntries()
    loadRssFeed( url ) {
        JavaRssReader.loadFeed (
            url,
            (err) => {
                console.error( err );
            },
            (result) => {
                this.setState( {
                    documentId: result
                });
                
                this.getEntries();
            }
        )
    }

    // This method filters all <entry> objects from the rss feed, and returns the id from each as an array of strings
    // This is used by RssEntry as an identifier
    getEntries() {
        if( this.state.documentId == "" )
            return;

        JavaRssReader.getEntries (
            this.state.documentId,
            (err) => {
                console.error( err );
            },
            (result) => {
                console.log( result );
                this.setState( {
                    entries: result
                });
            }
        )
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
        title: {
            fontSize: 32,
            fontWeight: 'bold',
        },
    }
)