import React, {Component} from 'react'
import {NativeModules, View, Text} from 'react-native'

var StringResourceLoader = NativeModules.StringResourceLoader; 

/*
* This class displays a string obtained from [reference] via [language] and [locKey]
* [language] is a state at the Main class, while [locKey] is set when creating this object
* State:
* - output: the string to show
* - source: where the string came from, can be disabled by props: isShowSource
*/
export class LocalizedText extends Component
{
    constructor(props) {
        super(props);
        this.state = {
            output: "",
            source: ""
        }
    }

    componentDidUpdate (previousProps, previousState)
    {
        // update localization data whenever a change happens on the loader component
        var reference = this.props.reference;
        var locKey = this.props.locKey;
        var language = this.props.language;
        
        if( !reference )
        {
            this.getBackupLocalization( language, locKey, previousState );
        }
        else
        {
            try {
                var fetchedValue = reference[locKey][language];
                if( fetchedValue )
                    this.updateState(previousState, fetchedValue, 'fetched');
                else
                    this.getBackupLocalization( language, locKey, previousState );
            } catch (error) {
                this.getBackupLocalization( language, locKey, previousState );
            }
        }
    }

    render ()
    {
        return (
            <View>
                <Text> {this.state.output} </Text>
                {
                    this.props.isShowSource ?
                    <Text> from: {this.state.source} </Text> :
                    null
                }
            </View>
        );
    }


    // get android-native-string-resource localization data
    getBackupLocalization( language, locKey, previousState )
    {
        // Get string from backup
        StringResourceLoader.getLocalizedString ( 
            language,
            locKey,
            (err) => {
                this.updateState(previousState, '\"'+locKey+'\": undefined', 'backup')
            },
            (result) => {
                this.updateState(previousState, result, 'backup');
            }
        );
    }

    // updates localization if it doesn't match the previous state
    updateState (previousState, newOutput, newSource)
    {
        // console.log( "output:" + previousState.output + " " + newOutput + " " + (previousState.output == newOutput) )
        // console.log( "source:" + previousState.source + " " + newSource + " " + (previousState.source == newSource) )
        if( previousState.output == newOutput && previousState.source == newSource )
            return;
        
        this.setState( { 
            output: newOutput,
            source: newSource
        });
    }
}


import { StyleSheet } from 'react-native'
const styles = StyleSheet.create(
    {
    }
)