package com.section_01.xml_parser.Xml;

import android.util.Xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class RssXmlEntry
{
    public RssXmlEntry ( Element element )
    {
        linkList = new ArrayList<XmlLink> ();
        authorList = new ArrayList<String> ();

        this.updated = XmlUtility.GetFirstNodeValue( element, "updated" );
        this.published = XmlUtility.GetFirstNodeValue( element, "published" );

        this.id = XmlUtility.GetFirstNodeValue( element, "id" );
        this.title = XmlUtility.GetFirstNodeValue( element, "title" );

        Node author = XmlUtility.GetFirstNode( element, "author", Node.ELEMENT_NODE );
        Element authorElement = (Element)(author);
        if( authorElement != null ) {
            NodeList nodeList = authorElement.getElementsByTagName("name");
            for( int i = 0; i < nodeList.getLength(); i++ ) {
                authorList.add( XmlUtility.GetNodeValue( nodeList.item(i)) );
            }
        }

        Node contentNode = XmlUtility.GetFirstNode( element, "content", Node.ELEMENT_NODE );
        this.content = XmlUtility.ParseContent( contentNode );

        NodeList link = element.getElementsByTagName("link");
        for( int i = 0; i < link.getLength(); i++ ) {
            linkList.add( XmlUtility.ParseLink(link.item(i)) );
        }
    }

    public String updated;
    public String published;

    public String id;
    public String title;
    public ArrayList<String> authorList;

    public XmlContent content;
    public ArrayList<XmlLink> linkList;

    // Checks for media content
    public boolean hasEnclosure ()
    {
        if( linkList.isEmpty() )
            return false;

        for( int i = 0; i < linkList.size(); i++ ) {
            XmlLink link = linkList.get(i);
            if( link != null && link.rel.equalsIgnoreCase("enclosure") )
                return true;
        }
        return false;
    }

    // Checks for "read more" link
    public String getLink ()
    {
        if( linkList.isEmpty() )
            return "";

        for( int i = 0; i < linkList.size(); i++ ) {
            XmlLink link = linkList.get(i);
            if( link != null && link.rel.equalsIgnoreCase("alternate") )
                return link.href;
        }
        return "";
    }

}
