package com.section_01.xml_parser;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.section_01.xml_parser.Xml.RssXmlEntry;
import com.section_01.xml_parser.Xml.RssXmlRoot;
import com.section_01.xml_parser.Xml.XmlParser;

import org.w3c.dom.Element;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RssReaderModule extends ReactContextBaseJavaModule {

    public RssReaderModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "JavaRssReader";
    }

    // Cache
    Map<String, RssXmlRoot> loadedXmls = null;

    // Utility
    private RssXmlRoot GetXmlDocument( String documentId )
    {
        if( loadedXmls == null )
            return null;
        return loadedXmls.get( documentId );
    }

    private RssXmlEntry GetXmlEntry( String documentId, String entryId )
    {
        RssXmlRoot document = GetXmlDocument( documentId );
        if( document != null ) {
            for( int i = 0; i < document.entries.length; i++ ) {
                String id = document.entries[i].id;
                if ( id.equalsIgnoreCase(entryId) ) {
                    return document.entries[i];
                }
            }
        }
        return null;
    }

    // React Methods
    /**
     * Loads the xml/rss feed
     * This is only loaded in this module, and its entries can be accessed via keys like a dictionary
     */
    @ReactMethod
    public void loadFeed( String url, Callback errorCallback, Callback successCallback )
    {
        if( loadedXmls == null )
            loadedXmls = new HashMap<String, RssXmlRoot>();

        try {
            // successCallback.invoke( XmlParser.ParseXml( url ) );
            RssXmlRoot newRoot = XmlParser.ParseXml( url );
            if( !loadedXmls.containsKey( newRoot.id ) )
                loadedXmls.put( newRoot.id, newRoot );
            successCallback.invoke( newRoot.id );
        } catch (Exception e) {
            errorCallback.invoke( e.getMessage() );
        }
    }

    /**
     * Gets the id of all entries found in the xmlDocument as an array of strings
     * These can be used as keys to get entries
     */
    @ReactMethod
    public void getEntries( String documentId, Callback errorCallback, Callback successCallback )
    {
        RssXmlRoot document = GetXmlDocument( documentId );
        if( document == null ) {
            errorCallback.invoke("null");
        }
        WritableArray writable = Arguments.createArray();
        for( int i = 0; i < document.entries.length; i++ ) {
            writable.pushString(document.entries[i].id);
        }
        successCallback.invoke( writable );
    }

    /**
     * Gets entry display parameters from an xmlDocument using documentId and entryId
     */
    @ReactMethod
    public void getEntrySummary( String documentId, String entryId, Callback errorCallback, Callback successCallback ) {
        RssXmlEntry entry = GetXmlEntry( documentId, entryId );
        WritableArray authors = Arguments.createArray();
        for( int i = 0; i < entry.authorList.size(); i++ )
            authors.pushString(entry.authorList.get(i));

        WritableMap map = Arguments.createMap();
        map.putString( "title", entry.title );
        map.putString( "content", entry.content.content );
        map.putString( "link", entry.getLink() );
        map.putString( "updated", entry.updated );
        map.putArray( "authors", authors );
        successCallback.invoke( map );
    }

    @ReactMethod
    public void getEntryContent( String documentId, String entryId, Callback errorCallback, Callback successCallback ) {

    }
}
