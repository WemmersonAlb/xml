package com.example;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Questao1 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("CaetanoVeloso.xml"));
        NodeList nList = doc.getElementsByTagName("album");
        System.out.println("<ul>");
        for(int i = 0 ; i < nList.getLength() ; i++){
            Element album = (Element) nList.item(i);
            NodeList authors = album.getElementsByTagName("author");
            if(authors.getLength()>1){
                Node title = album.getElementsByTagName("title").item(0);
                Node text = title.getFirstChild();
                System.out.println(String.format("  <li>%s</li>", text.getNodeValue()));
            }
        }

        System.out.println("</ul>");
    }
}
