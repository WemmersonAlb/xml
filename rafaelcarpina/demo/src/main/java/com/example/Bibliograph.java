package com.example;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bibliography")
public class Bibliograph {
    private List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        
        return String.format("Bibliograph\n%s", book);
    }
}
