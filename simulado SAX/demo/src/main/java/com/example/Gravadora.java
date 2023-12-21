package com.example;

public class Gravadora {
    private String nome;
    private int qtdAlbums;

    public Gravadora(){}

    public Gravadora(String nome, int qtdAlbums) {
        this.nome = nome;
        this.qtdAlbums = qtdAlbums;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getQtdAlbums() {
        return qtdAlbums;
    }
    public void setQtdAlbums(int qtdAlbums) {
        this.qtdAlbums = qtdAlbums;
    }
}
