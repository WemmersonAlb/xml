package com.example;

import java.net.MalformedURLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class JABAX {
    public static void main(String[] args) throws JAXBException, MalformedURLException, URISyntaxException {
        
        JAXBContext context = JAXBContext.newInstance(Bibliograph.class);
        // Marshaller mar = context.createMarshaller();
        // mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // mar.marshal(p, System.out);
        //JAXBContext context = JAXBContext.newInstance(Endereco.class);
        Unmarshaller um = context.createUnmarshaller();
        Bibliograph b = (Bibliograph) um.unmarshal(new StreamSource("bibliography.xml"));
        System.out.println(b.toString());
        //Endereco e = (Endereco) um.unmarshal(new URL("https://viacep.com.br/ws/50800270/xml"));
        //URL url = new URI("https://viacep.com.br/ws/50800270/xml").toURL();
        // Endereco e = (Endereco) um.unmarshal(url);
        // Pessoa p = new Pessoa();
        // p.setNome("Anderson");
        // p.setEndereco(e);

        // System.out.println(p);



    }
}
