package org.example.models;

public class Cliente {
    private int id_cliente;
    private String nm_cliente;
    private Long cpf_cliente;
    private String genero_cliente;
    private long telefone_cliente;
    private String dt_nasc_cliente;
    private String endereco_cliente;
    private String email_cliente;
    private String senha_cliente;


    // Construtor
    public Cliente(int id_cliente, String nm_cliente, Long cpf_cliente, String genero_cliente, long telefone_cliente, String dt_nasc_cliente, String endereco_cliente, String email_cliente, String senha_cliente) {
        this.id_cliente = id_cliente;
        this.nm_cliente = nm_cliente;
        this.cpf_cliente = cpf_cliente;
        this.genero_cliente = genero_cliente;
        this.telefone_cliente = telefone_cliente;
        this.dt_nasc_cliente = dt_nasc_cliente;
        this.endereco_cliente = endereco_cliente;
        this.email_cliente = email_cliente;
        this.senha_cliente = senha_cliente;
    }
    public Cliente() {}


    // GetterSetter
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNm_cliente() {
        return nm_cliente;
    }

    public void setNm_cliente(String nm_cliente) {
        this.nm_cliente = nm_cliente;
    }

    public Long getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(Long cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getGenero_cliente() {
        return genero_cliente;
    }

    public void setGenero_cliente(String genero_cliente) {
        this.genero_cliente = genero_cliente;
    }

    public long getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(long telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getDt_nasc_cliente() {
        return dt_nasc_cliente;
    }

    public void setDt_nasc_cliente(String dt_nasc_cliente) {
        this.dt_nasc_cliente = dt_nasc_cliente;
    }

    public String getEndereco_cliente() {
        return endereco_cliente;
    }

    public void setEndereco_cliente(String endereco_cliente) {
        this.endereco_cliente = endereco_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getSenha_cliente() {
        return senha_cliente;
    }

    public void setSenha_cliente(String senha_cliente) {
        this.senha_cliente = senha_cliente;
    }
}
