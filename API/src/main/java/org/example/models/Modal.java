package org.example.models;

public class Modal {
    private int id_modal;
    private Medida id_medida;
    private String modelo_modal;
    private String placa_modal;
    private String marca_modal;
    private int ano_modal;
    private String tipo_modal;


    // Construtor
    public Modal(int id_modal, Medida id_medida, String modelo_modal, String placa_modal, String marca_modal, int ano_modal, String tipo_modal) {
        this.id_modal = id_modal;
        this.id_medida = id_medida;
        this.modelo_modal = modelo_modal;
        this.placa_modal = placa_modal;
        this.marca_modal = marca_modal;
        this.ano_modal = ano_modal;
        this.tipo_modal = tipo_modal;
    }
    public Modal() {}



    // GetterSetter
    public int getId_modal() {
        return id_modal;
    }

    public void setId_modal(int id_modal) {
        this.id_modal = id_modal;
    }

    public Medida getId_medida() {
        return id_medida;
    }

    public void setId_medida(Medida id_medida) {
        this.id_medida = id_medida;
    }

    public String getModelo_modal() {
        return modelo_modal;
    }

    public void setModelo_modal(String modelo_modal) {
        this.modelo_modal = modelo_modal;
    }

    public String getPlaca_modal() {
        return placa_modal;
    }

    public void setPlaca_modal(String placa_modal) {
        this.placa_modal = placa_modal;
    }

    public String getMarca_modal() {
        return marca_modal;
    }

    public void setMarca_modal(String marca_modal) {
        this.marca_modal = marca_modal;
    }

    public int getAno_modal() {
        return ano_modal;
    }

    public void setAno_modal(int ano_modal) {
        this.ano_modal = ano_modal;
    }

    public String getTipo_modal() {
        return tipo_modal;
    }

    public void setTipo_modal(String tipo_modal) {
        this.tipo_modal = tipo_modal;
    }
}
