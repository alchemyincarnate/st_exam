import React, {Component} from 'react'
import {View, Text, ActivityIndicator} from 'react-native'

/*
 * Loads the necessary localization files on startup
 * Displays an ActivityIndicator while it is still loading
*/
export class LocalizationLoader extends Component
{
    state = {
        loading: false
    };

    componentDidMount()
    {
        // loading the localization file json
        this.loadLocalizationFiles( this.props.url );
    }

    render ()
    {
        return (
            this.state.loading ?
            <View style={styles.container}>
                <View style={styles.loadingBG}>
                    <ActivityIndicator size = {"large"} color="#0000ff" />
                    <Text> {"Loading ..."} </Text>
                </View>
            </View>
            : null
        );
    }

    // Performs a get on a specified url
    // Hardcoded to get json only
    loadLocalizationFiles( url )
    {
        this.setLoading( true );
        return fetch( url, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            }
        })
        .then( (response) => response.json() )
        .then( (responseJson) => {
            this.props.onLoad( responseJson )
            this.setLoading( false );
        })
        .catch((error) =>{
            console.error(error);
            this.setLoading( false );
        });
    }

    // Set current loading state
    setLoading( isLoading )
    {
        this.setState( {
            loading: isLoading
        });
    }
}

import { StyleSheet } from 'react-native'
const styles = StyleSheet.create(
    {
        container: {
            position: 'absolute',
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
            justifyContent: 'center',
            alignItems: 'center',
            backgroundColor: 'rgba(0,0,0, 0.3)'
        },
        loadingBG: {
            width: "50%",
            height: "18%",
            justifyContent: 'center',
            alignItems: 'center',
            backgroundColor: 'rgba(255,255,255, 1.0)'
        }
    }
)