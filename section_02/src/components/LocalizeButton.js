import React, {Component} from 'react'
import {TouchableOpacity, View, Text} from 'react-native'
import {LocalizedText} from './LocalizedText.js'

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

    setLang = () =>
    {
        // set language to the language defined
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