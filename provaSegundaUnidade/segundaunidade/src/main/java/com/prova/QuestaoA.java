package com.prova;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Serializer.Property;

public class QuestaoA extends DefaultHandler{

    //Mecanismo de escrita
    private XMLStreamWriter xsw;

    public void inicializadorDoStreammingDeEscrita() throws XMLStreamException, IOException, SaxonApiException {
        Configuration config = new Configuration();
        Processor p = new Processor(config);
        Serializer s = p.newSerializer();
        s.setOutputProperty(Property.METHOD, "xml");
        s.setOutputProperty(Property.INDENT, "yes");
        s.setOutputFile(new File("xmlTratado.xml"));
        try{
            this.xsw = s.getXMLStreamWriter();
            this.xsw.writeStartDocument();
        }catch(XMLStreamException e){
            e.printStackTrace();
        }
    }

    //Lógica SAX
    boolean vFieldCountry = false;
    String paisAtual = "";
    boolean vFieldYear = false;
    String year = "";
    boolean vFieldValue = false;
    String value = "";
    boolean vRegion = false;

    boolean primeiroElemento = true;


    @Override
    public void startDocument(){

            try {
                this.xsw.writeStartElement("population");
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
    }
    @Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException{
            if(qName.equalsIgnoreCase("field")){
                if(atts.getValue(0).equalsIgnoreCase("Country")){
                    vFieldCountry = true;
                }else if(atts.getValue(0).equalsIgnoreCase("Year")){
                    vFieldYear = true;
                }else if(atts.getValue(0).equalsIgnoreCase("Value")){
                    vFieldValue = true;
                }else if(atts.getValue(0).equalsIgnoreCase("Region")){
                    vRegion = true;
                }
            }
    }
    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
            if(vFieldCountry){
                if(primeiroElemento){
                    primeiroElemento = false;
                    paisAtual = new String(ch, start, length);
                    try {
                        xsw.writeStartElement("record");
                        xsw.writeAttribute("country", paisAtual);
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }

                }else if(!paisAtual.equalsIgnoreCase(new String(ch, start, length))){
                    paisAtual = new String(ch, start, length);
                    try {
                        xsw.writeEndElement();
                        xsw.writeStartElement("record");
                        xsw.writeAttribute("country", paisAtual);
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                }
                vFieldCountry = false;
            }else if(vFieldYear&&vRegion==false){
                year = new String(ch, start, length);
                vFieldYear = false;
            }else if(vFieldValue&&vRegion==false){
                value = new String(ch, start, length);
                try {
                    xsw.writeStartElement("value");
                    xsw.writeAttribute("year", year);
                    xsw.writeCharacters(value);
                    xsw.writeEndElement();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
                vFieldValue = false;
            }else if(vRegion){

                if(!paisAtual.equalsIgnoreCase(new String(ch, start, length))){
                    paisAtual = new String(ch, start, length);
                    try {
                        xsw.writeEndElement();
                        xsw.writeStartElement("record");
                        xsw.writeAttribute("region", paisAtual);
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                }
                vRegion = false;
            }
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{

    }
    @Override
    public void endDocument() throws SAXException{
        try {
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws XMLStreamException, IOException, SaxonApiException {
        File inputFile = new File("population.xml");
        QuestaoA userHandle = new QuestaoA();
        userHandle.inicializadorDoStreammingDeEscrita();

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userHandle);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}