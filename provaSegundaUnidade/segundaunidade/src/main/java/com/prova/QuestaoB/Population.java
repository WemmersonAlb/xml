package com.prova.QuestaoB;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "population")
public class Population {
    private List<Record> record;

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    public void imprimirPopulation(){
        System.out.println("Population:");
        for(Record i : record){
            i.imprimirRecord();
        }

    }
}
