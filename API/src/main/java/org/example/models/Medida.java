package org.example.models;

import java.sql.Timestamp;

public class Medida {
    private int id;
    private double altura;
    private double largura;
    private double comprimento;
    private double peso;
    private double peso_suportado;


    //Construtor
    public Medida(int id, double altura, double largura, double comprimento, double peso, double peso_suportado) {
        this.id = id;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.peso = peso;
        this.peso_suportado = peso_suportado;
    }
    public Medida() {}



    // GetterSetter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPeso_suportado() {
        return peso_suportado;
    }

    public void setPeso_suportado(double peso_suportado) {
        this.peso_suportado = peso_suportado;
    }
}
