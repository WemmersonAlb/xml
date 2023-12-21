package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TerceiraQuestao extends DefaultHandler{
    ArrayList<Gravadora> gravadoras = new ArrayList<Gravadora>();
    boolean vGravadora = false;
    @Override
    public void startDocument() throws SAXException{
        System.out.println("<companies>");
    }
    @Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException{
        if(qName.equalsIgnoreCase("company")){
            vGravadora = true;
        }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        if(vGravadora){
            boolean verificaSeExisteNaLista = false;
            for(Gravadora n : gravadoras){
                if(n.getNome().equalsIgnoreCase(new String(ch, start, length))){
                    n.setQtdAlbums(n.getQtdAlbums()+1);
                    verificaSeExisteNaLista = true;
                    break;
                }
            }
            if(!verificaSeExisteNaLista){
                gravadoras.add(new Gravadora(new String(ch, start, length), 1));
            }
            vGravadora = false;
        }
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
    }
    @Override
    public void endDocument() throws SAXException{
        Collections.sort(gravadoras, new CompareCustom());
        for(Gravadora g : gravadoras){
            System.out.println(String.format("  <company name=\"%s\" count=\"%d\"/>", g.getNome(), g.getQtdAlbums()));
        }
        System.out.println("</companies>");
    }
    public static void main(String[] args) {
        File inputFile = new File("CaetanoVeloso.xml");
        TerceiraQuestao terceiraQuestao = new TerceiraQuestao();

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, terceiraQuestao);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
