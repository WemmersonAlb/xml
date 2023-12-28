package com.prova.QuestaoB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class TestJAxB {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Population.class);
        Unmarshaller um = context.createUnmarshaller();
        Population p = (Population) um.unmarshal(new StreamSource("xmlTratado.xml"));
        p.imprimirPopulation();
    }

}
