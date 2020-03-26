package com.section_01.xml_parser.Xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class RssXmlRoot
{
    public String title;
    public String icon;
    public String updated;
    public String id;
    public XmlLink link;
    public RssXmlEntry[] entries;

    /**
     * A simple wrapper for an rssfeed xmlObject
     * @param xmlObject the xml string
     */
    public RssXmlRoot( Element xmlObject ) {
        if( xmlObject == null )
            return;
        this.title = XmlUtility.GetFirstNodeValue( xmlObject, "title" );
        this.icon = XmlUtility.GetFirstNodeValue( xmlObject, "icon" );
        this.updated = XmlUtility.GetFirstNodeValue( xmlObject, "updated" );
        this.id = XmlUtility.GetFirstNodeValue( xmlObject, "id" );

        Node firstLink = XmlUtility.GetFirstNode( xmlObject, "link", Node.ELEMENT_NODE );
        this.link = XmlUtility.ParseLink( firstLink );

        ArrayList<Element> entryList = XmlUtility.GetElements( xmlObject, "entry" );
        this.entries = new RssXmlEntry[ entryList.size() ];
        for( int i = 0; i < entries.length; i++ )
            entries[i] = new RssXmlEntry( entryList.get(i) );
    }
}
