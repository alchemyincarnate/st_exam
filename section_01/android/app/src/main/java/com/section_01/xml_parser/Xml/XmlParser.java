package com.section_01.xml_parser.Xml;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.net.URL;

public class XmlParser {

    private static DocumentBuilder s_DocumentBuilder;
    private static SchemaFactory s_XmlSchemaFactory;

    // https://howtodoinjava.com/xml/read-xml-dom-parser-example/
    public static RssXmlRoot ParseXml( String url ) throws Exception
    {
        // TODO: fix schema validation.. currently throwing null
        try {
            if( s_DocumentBuilder == null )
                s_DocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           // if( s_XmlSchemaFactory == null )
           //     s_XmlSchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        URL xmlUrl = new URL( url );
        //Schema schema = s_XmlSchemaFactory.newSchema( xmlUrl );
        //Validator xmlValidator = schema.newValidator();

        Document xmlFile = s_DocumentBuilder.parse( xmlUrl.openStream() );
        try {
           // xmlValidator.validate( new DOMSource( xmlFile ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // return xmlFile.getDocumentElement().getChildNodes().item(0).getNodeName();
        return new RssXmlRoot( xmlFile.getDocumentElement() );
    }
}
