package com.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class Title {
    @XmlAttribute
    private String lang;
    
    @XmlValue
    private String titulo;

    @Override
    public String toString() {
        
        return String.format("Titulo: %s\nLang: %s", titulo, lang);
    }
}
