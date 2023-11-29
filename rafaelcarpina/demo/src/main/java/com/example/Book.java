package com.example;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @XmlAttribute
    private String category;

    private Title title;
    private List<String> author;
    private int year;
    private double price;
    
    @Override
    public String toString() {
        
        return String.format("Book\nCategoria %s\n%s\nAuthor %s\nYear %d\nPrice %f\n\n\n", category, title, author, year, price);
    }
}
