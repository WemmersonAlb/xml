package com.example;
import java.util.Comparator;

public class CompareCustom implements Comparator<Gravadora>{
    public int compare(Gravadora a, Gravadora b){
        if(a.getQtdAlbums()>b.getQtdAlbums()){
            return -1;
        }else if(a.getQtdAlbums()==b.getQtdAlbums()){
            return 0;
        }else{
            return 1;
        }
    }
}
