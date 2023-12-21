package com.example;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandle extends DefaultHandler {
    boolean vAuthor = false;
    boolean vTitle = false;
    String title;
    int author = 0;

    @Override
    public void startDocument() throws SAXException{
        System.out.println("<ul>");
    }
    @Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException{
        if(qName.equalsIgnoreCase("title")){
            vTitle = true;
        }else if(qName.equalsIgnoreCase("author")){
            author++;
        }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        if(vTitle){
            title = new String(ch, start, length);
            vTitle = false;
        }
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
        if(qName.equalsIgnoreCase("authors")){
            if(author>1){
                System.out.println(String.format("  <li>%s</li>", title));
            }
            author = 0;
        }
    }
    @Override
    public void endDocument() throws SAXException{
        System.out.println("</ul>");
    }
    public static void main(String[] args) {
        File inputFile = new File("CaetanoVeloso.xml");
        UserHandle userHandle = new UserHandle();

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userHandle);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
