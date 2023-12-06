package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

public class StAX {
  public static void main1(String[] args) throws XMLStreamException, FileNotFoundException {
    XMLInputFactory xmlif = XMLInputFactory.newFactory();
    XMLEventReader xsr = xmlif.createXMLEventReader(new FileReader("bibliography.xml"));

    boolean bTitle = false;
    String title = null;
    int qtdAuthor = 0;
    int qtdBooks = 0;

    while (xsr.hasNext()) {
      XMLEvent event = xsr.nextEvent();
      String qName;
      switch (event.getEventType()) {
        case XMLStreamConstants.START_ELEMENT:
          qName = event.asStartElement().getName().getLocalPart();
          if (qName.equalsIgnoreCase("title")) {
            bTitle = true;
          } else if (qName.equalsIgnoreCase("author")) {
            qtdAuthor++;
          }
          break;
        case XMLStreamConstants.CHARACTERS:
          if (bTitle) {
            title = event.asCharacters().getData();
            bTitle = false;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          qName = event.asEndElement().getName().getLocalPart();
          if (qName.equalsIgnoreCase("book")) {
            if (qtdAuthor > 1) {
              System.out.println(title);
              qtdBooks++;
            }
            title = null;
            qtdAuthor = 0;
          }
          break;
        case XMLStreamConstants.END_DOCUMENT:
          System.out.println(qtdBooks);
          break;
      }
    }
    xsr.close();
  }

  public static void main(String[] args) throws XMLStreamException, FileNotFoundException, IOException {
    // File reader = new File("abc.txt");
    // System.out.println(reader.getAbsolutePath());
    XMLInputFactory xmlif = XMLInputFactory.newFactory();
    XMLOutputFactory xmlof = XMLOutputFactory.newFactory();
    XMLStreamReader xsr = xmlif.createXMLStreamReader(new FileReader("xml/rafaelcarpina/bibliography.xml"));
    XMLStreamWriter xsw = xmlof.createXMLStreamWriter(new FileWriter("agrvai.xml"));

    boolean chaveYear = false;
    boolean chaveTitle = false;
    boolean chaveAuthor = false;
    boolean chavePrice = false;

    String title = null;
    List<String> authors = new ArrayList<String>();
    int year = 0;
    String price = null;

    xsw.writeStartDocument();
    xsw.writeStartElement("result");

    while (xsr.hasNext()) {
      String qName;

      switch (xsr.next()) {
        case XMLStreamConstants.START_ELEMENT:
          qName = xsr.getLocalName();
          if (qName.equalsIgnoreCase("title")) {
            chaveTitle = true;
          } else if (qName.equalsIgnoreCase("author")) {
            chaveAuthor = true;
          } else if (qName.equalsIgnoreCase("year")) {
            chaveYear = true;
          } else if (qName.equalsIgnoreCase("price")) {
            chavePrice = true;
          }

          break;
        case XMLStreamConstants.CHARACTERS:

          qName = xsr.getText();
          if (chaveTitle) {
            title = qName;
            chaveTitle = false;
          } else if (chaveAuthor) {
            authors.add(qName);
            chaveAuthor = false;
          } else if (chaveYear) {
            year = Integer.parseInt(qName);
            chaveYear = false;
          } else if (chavePrice) {
            price = qName;
            chavePrice = false;
          }

          break;
        case XMLStreamConstants.END_ELEMENT:
          qName = xsr.getLocalName();
          if (qName.equalsIgnoreCase("book")) {
            if (year > 2010) {
              xsw.writeStartElement("book");
              xsw.writeStartElement("title");
              xsw.writeCharacters(title);
              xsw.writeEndElement();
              for (String author : authors) {
                xsw.writeStartElement("author");
                xsw.writeCharacters(author);
                xsw.writeEndElement();
              }
              xsw.writeStartElement("year");
              xsw.writeCharacters(String.format("%d", year));
              xsw.writeEndElement();

              xsw.writeStartElement("price");
              xsw.writeCharacters(price);
              xsw.writeEndElement();
              xsw.writeEndElement();
            }
            authors.clear();
          }
          break;
        case XMLStreamConstants.END_DOCUMENT:
          break;
      }
    }
    xsw.writeEndDocument();
    xsr.close();
    xsw.close();
  }
}