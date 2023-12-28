package com.prova;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.sf.saxon.Configuration;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Serializer.Property;

public class QuestaoC {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SaxonApiException, XMLStreamException {
        //Configuração do documento de saída
        Configuration config = new Configuration();
        Processor p = new Processor(config);
        Serializer s = p.newSerializer();

        s.setOutputProperty(Property.METHOD, "xml");
        s.setOutputProperty(Property.INDENT, "yes");
        s.setOutputFile(new File("questaoC.xml"));
        XMLStreamWriter xsw = s.getXMLStreamWriter();
        xsw.writeStartDocument();//Abertura do documento de saída
        xsw.writeStartElement("result");

        //Abrindo arquivo com DOM
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("xmlTratado.xml"));

        //Lógica
        NodeList paisesList = doc.getElementsByTagName("record");
        double variacaoRelativaGlobal = 0.0;
        double variacaoAbsolutaGlobal = 0.0;
        String paisRelativo = "";
        String paisAbsoluto = "";



        for(int i = 0 ; i < paisesList.getLength() ; i++){
            Element pais = (Element) paisesList.item(i);
            String paisAtual = pais.getAttribute("country");//Pega o atual pais do laço
            if(paisAtual.isEmpty()){
                continue;
            }

            NodeList anosList = pais.getElementsByTagName("value");

            Double primeiroAno = 0.0;
            for(int j = 0 ; j < anosList.getLength() ; j++){
                Node valorPopulacional = anosList.item(j);
                Node text = valorPopulacional.getFirstChild();
                String primeiroAnoString = text.getNodeValue();
                try {
                    primeiroAno = Double.parseDouble(primeiroAnoString); //Pega a população do primeiro ano

                    if(primeiroAno>0.0){
                        break;
                    }
                } catch (Exception e) {
                }

            }
            Double ultimoAno = 0.0;//Pega a população do último ano
            for(int k = anosList.getLength()-1 ; k>1 ; k--){
                Node valorPopulacional = anosList.item(k);
                Node text = valorPopulacional.getFirstChild();
                String ultimoAnoString = text.getNodeValue();
                try {
                    ultimoAno = Double.parseDouble(ultimoAnoString);

                    if(ultimoAno>0.0){
                        break;
                    }
                } catch (Exception e) {
                }
            }

            if(ultimoAno == 0.0 && primeiroAno == 0.0){
                continue;
            }
            double variacaoAbsolutaAtual = ultimoAno - primeiroAno;
            if( variacaoAbsolutaAtual > variacaoAbsolutaGlobal ){
                variacaoAbsolutaGlobal = variacaoAbsolutaAtual;
                paisAbsoluto = paisAtual;
            }

            double variacaoRelativaAtual = ((ultimoAno*100)/primeiroAno)-100;
            if( variacaoRelativaAtual > variacaoRelativaGlobal ){
                variacaoRelativaGlobal = variacaoRelativaAtual;
                paisRelativo = paisAtual;
            }
        }

        xsw.writeStartElement("relative");
        xsw.writeAttribute("country", paisRelativo);
        xsw.writeAttribute("value", String.format("%.2f", variacaoRelativaGlobal));
        xsw.writeEndElement();

        xsw.writeStartElement("absolute");
        xsw.writeAttribute("country", paisAbsoluto);
        xsw.writeAttribute("value", String.format("%.2f", variacaoAbsolutaGlobal));
        xsw.writeEndElement();

        xsw.writeEndElement();
        xsw.close();

        System.out.println("<result>");
        System.out.println(String.format("  <relative country=\"%s\" value=\"%.2f\"/>", paisRelativo, variacaoRelativaGlobal));
        System.out.println(String.format("  <absolute country=\"%s\" value=\"%.2f\"/>", paisAbsoluto, variacaoAbsolutaGlobal));
        System.out.println("</relative>");
    }
}
