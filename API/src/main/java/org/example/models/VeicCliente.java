package org.example.models;

public class VeicCliente {
    private int id_veic_client;
    private Cliente id_cliente;
    private Veiculo id_veiculo;



    public VeicCliente(int id_veic_client, Cliente id_cliente, Veiculo id_veiculo) {
        this.id_veic_client = id_veic_client;
        this.id_cliente = id_cliente;
        this.id_veiculo = id_veiculo;
    }
    public VeicCliente(){}



    public int getId_veic_client() {
        return id_veic_client;
    }

    public void setId_veic_client(int id_veic_client) {
        this.id_veic_client = id_veic_client;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Veiculo getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Veiculo id_veiculo) {
        this.id_veiculo = id_veiculo;
    }
}
