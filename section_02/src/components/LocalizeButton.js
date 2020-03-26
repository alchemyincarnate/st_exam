import React, {Component} from 'react'
import {TouchableOpacity, View, Text} from 'react-native'
import {LocalizedText} from './LocalizedText.js'

/*
* A Button that displays a LocalizedText and allows changing of the Main language
* [language] is independent from Main language, and Main language is changed to this value when this button is pressed
*/
export class LocalizeButton extends Component
{
    constructor(props) {
        super(props);
    }

    render ()
    {
        return (
            <TouchableOpacity
                style={styles.button}
                onPress={this.setLang}>
                <LocalizedText
                    reference={this.props.reference}
                    language={this.props.language}
                    locKey='REFRESH'
                />
            </TouchableOpacity>
        );
    }

    // set language to the language defined
    setLang = () =>
    {
        this.props.onSetLang( this.props.language );
    }
}


import { StyleSheet } from 'react-native'
const styles = StyleSheet.create(
    {
        button: {
            alignItems: 'center',
            backgroundColor: '#DDDDDD',
            padding: 10
        },
        buttonText: {
            color: '#FF00FF',
            padding: 10
        },
    }
)