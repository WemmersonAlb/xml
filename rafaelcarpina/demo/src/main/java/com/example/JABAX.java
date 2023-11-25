package com.example;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JABAX {
    public static void main(String[] args) throws JAXBException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(Endereco.class);
        Unmarshaller um = context.createUnmarshaller();
        Endereco e = (Endereco) um.unmarshal(new URL("https://viacep.com.br/ws/50800270/xml"));
        Pessoa p = new Pessoa();
        p.setNome("Anderson");
        p.setEndereco(e);

        System.out.println(p);



    }
}
