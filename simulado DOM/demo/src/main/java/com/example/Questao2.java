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

public class Questao2 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("CaetanoVeloso.xml"));
        NodeList nList = doc.getElementsByTagName("album");
        int qttAlbunsPorAno = 0;
        int maiorQtt = 0;
        int anoAtual = 0;
        int maiorAno = 0;
        for(int i = 0 ; i < nList.getLength() ; i++){
            Element album = (Element) nList.item(i);
            int temp =  Integer.parseInt(album.getAttribute("year"));
            if(anoAtual != temp){
                anoAtual = temp;
                qttAlbunsPorAno = 1;
            }else{
                qttAlbunsPorAno++;
            }

            if(qttAlbunsPorAno>maiorQtt){
                maiorQtt = qttAlbunsPorAno;
                maiorAno = anoAtual;
            }
        }
        System.out.println(String.format("<year count=\"%d\">%d</year> ", maiorQtt, maiorAno));
    }
}
