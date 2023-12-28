package com.prova.QuestaoB;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
    @XmlAttribute
    private String country;

    @XmlAttribute
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private List<Value> value;

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public void imprimirRecord(){
        if(this.country == null){
            System.out.println(" Região: "+this.region);
        }else{
            System.out.println(" País: "+this.country);
        }
        for(Value i : value){
            i.imprimirValue();
        }
    }
}
