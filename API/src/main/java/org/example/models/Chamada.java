package org.example.models;

import java.sql.Timestamp;

public class Chamada {
    private int id_chamada;
    private ModalColab id_modal_colab;
    private VeicCliente id_veic_client;
    private String dt_inicio_chamada;
    private String dt_fim_chamada;
    private String local_chamada;
    private String destino_chamada;
    private String ds_prob_chamada;



    // Construtor

    public Chamada(int id_chamada, ModalColab id_modal_colab, VeicCliente id_veic_client, String dt_inicio_chamada, String dt_fim_chamada, String local_chamada, String destino_chamada, String ds_prob_chamada) {
        this.id_chamada = id_chamada;
        this.id_modal_colab = id_modal_colab;
        this.id_veic_client = id_veic_client;
        this.dt_inicio_chamada = dt_inicio_chamada;
        this.dt_fim_chamada = dt_fim_chamada;
        this.local_chamada = local_chamada;
        this.destino_chamada = destino_chamada;
        this.ds_prob_chamada = ds_prob_chamada;
    }

    public Chamada() {}



    // GetterSetter
    public int getId_chamada() {
        return id_chamada;
    }

    public void setId_chamada(int id_chamada) {
        this.id_chamada = id_chamada;
    }

    public ModalColab getId_modal_colab() {
        return id_modal_colab;
    }

    public void setId_modal_colab(ModalColab id_modal_colab) {
        this.id_modal_colab = id_modal_colab;
    }

    public VeicCliente getId_veic_client() {
        return id_veic_client;
    }

    public void setId_veic_client(VeicCliente id_veic_client) {
        this.id_veic_client = id_veic_client;
    }

    public String getDt_inicio_chamada() {
        return dt_inicio_chamada;
    }

    public void setDt_inicio_chamada(String dt_inicio_chamada) {
        this.dt_inicio_chamada = dt_inicio_chamada;
    }

    public String getDt_fim_chamada() {
        return dt_fim_chamada;
    }

    public void setDt_fim_chamada(String dt_fim_chamada) {
        this.dt_fim_chamada = dt_fim_chamada;
    }

    public String getLocal_chamada() {
        return local_chamada;
    }

    public void setLocal_chamada(String local_chamada) {
        this.local_chamada = local_chamada;
    }

    public String getDestino_chamada() {
        return destino_chamada;
    }

    public void setDestino_chamada(String destino_chamada) {
        this.destino_chamada = destino_chamada;
    }

    public String getDs_prob_chamada() {
        return ds_prob_chamada;
    }

    public void setDs_prob_chamada(String ds_prob_chamada) {
        this.ds_prob_chamada = ds_prob_chamada;
    }
}
