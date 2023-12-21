package com.example;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SegundaQuestao extends DefaultHandler {
    int qtdAlbunsAtual = 0;
    String yearAlbumAtual = "";
    int qtdAlbunsMaior = 0;
    String yearAlbumMaior = "";

    @Override
    public void startDocument() throws SAXException{

    }
    @Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException{
        if(qName.equalsIgnoreCase("album")){
            if(yearAlbumAtual.equalsIgnoreCase(atts.getValue(0))){
                qtdAlbunsAtual++;
                if(qtdAlbunsAtual>qtdAlbunsMaior){
                    qtdAlbunsMaior = qtdAlbunsAtual;
                    yearAlbumMaior = yearAlbumAtual;
                }
            }else{
                qtdAlbunsAtual = 0;
               yearAlbumAtual = atts.getValue(0);
                qtdAlbunsAtual++;
            }
        }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{

    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
    }
    @Override
    public void endDocument() throws SAXException{
        System.out.println(String.format("<year count=\"%d\">%s</year>", qtdAlbunsMaior, yearAlbumMaior));
    }
    public static void main(String[] args) {
        File inputFile = new File("CaetanoVeloso.xml");
        SegundaQuestao segundaQuestao = new SegundaQuestao();

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, segundaQuestao);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
