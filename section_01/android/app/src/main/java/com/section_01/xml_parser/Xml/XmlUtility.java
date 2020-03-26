package com.section_01.xml_parser.Xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class XmlUtility {

    public static Node GetFirstNode( Element xmlDocument, String id, short type )
    {
        NodeList nodeList = xmlDocument.getElementsByTagName( id );
        for( int i = 0 ; i < nodeList.getLength(); i++ ) {
            if( nodeList.item(i).getNodeType() == type ) {
                return nodeList.item(i);
            }
        }
        return null;
    }

    public static String GetNodeValue( Node node )
    {
        if( node != null ) {
            NodeList valueNodes = node.getChildNodes();
            for( int i = 0 ; i < valueNodes.getLength(); i++ ) {
                if( valueNodes.item(i).getNodeType() == Node.TEXT_NODE ) {
                    return valueNodes.item(i).getNodeValue();
                }
            }
        }
        return "";
    }

    public static String GetFirstNodeValue( Element xmlDocument, String id )
    {
        Node node = GetFirstNode( xmlDocument, id, Node.ELEMENT_NODE );
        if( node != null ) {
            return GetNodeValue( node );
        }
        return "";
    }

    public static ArrayList<Element> GetElements( Element xmlDocument, String id )
    {
        ArrayList<Element> retVal = new ArrayList<Element> ();
        NodeList nodeList = xmlDocument.getElementsByTagName( id );
        for( int i = 0; i < nodeList.getLength(); i++ ) {
            if( nodeList.item(i).getNodeType() == Node.ELEMENT_NODE )
                retVal.add( (Element)nodeList.item(i) );
        }
        return retVal;
    }

    public static XmlLink ParseLink( Node node ) {
        XmlLink retVal = new XmlLink();
        Element element = (Element)node;
        if( element != null )
        {
            retVal.rel = element.getAttribute("rel");
            retVal.type = element.getAttribute("type");
            retVal.href = element.getAttribute("href");
        }
        return retVal;
    }

    public static XmlContent ParseContent( Node node ) {
        XmlContent retVal = new XmlContent();
        Element element = (Element)node;
        if( element != null )
        {
            retVal.type = element.getAttribute("type");
            retVal.content = GetNodeValue( element );
        }
        return retVal;
    }
}
