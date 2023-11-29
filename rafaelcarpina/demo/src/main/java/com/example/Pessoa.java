package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {
    private String nome;
    private int idade;
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa(){};
    
    public Pessoa(String nome, int idade, Endereco end) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = end;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("\n\nNome: %s\n%s", nome, endereco.toString());
        
    }
}
