package org.example.models;

public class Colaborador {
    private int id_colab;
    private String nm_colab;
    private long cpf_colab;
    private String genero_colab;
    private long tel_colab;
    private String dt_nasc_colab;
    private String endereco_colab;
    private String email_colab;
    private String senha_colab;


    // Construtor
    public Colaborador(int id_colab, String nm_colab, long cpf_colab, String genero_colab, long tel_colab, String dt_nasc_colab, String endereco_colab, String email_colab, String senha_colab) {
        this.id_colab = id_colab;
        this.nm_colab = nm_colab;
        this.cpf_colab = cpf_colab;
        this.genero_colab = genero_colab;
        this.tel_colab = tel_colab;
        this.dt_nasc_colab = dt_nasc_colab;
        this.endereco_colab = endereco_colab;
        this.email_colab = email_colab;
        this.senha_colab = senha_colab;
    }
    public Colaborador() {}


    // GetterSetter
    public int getId_colab() {
        return id_colab;
    }

    public void setId_colab(int id_colab) {
        this.id_colab = id_colab;
    }

    public String getNm_colab() {
        return nm_colab;
    }

    public void setNm_colab(String nm_colab) {
        this.nm_colab = nm_colab;
    }

    public long getCpf_colab() {
        return cpf_colab;
    }

    public void setCpf_colab(long cpf_colab) {
        this.cpf_colab = cpf_colab;
    }

    public String getGenero_colab() {
        return genero_colab;
    }

    public void setGenero_colab(String genero_colab) {
        this.genero_colab = genero_colab;
    }

    public long getTel_colab() {
        return tel_colab;
    }

    public void setTel_colab(long tel_colab) {
        this.tel_colab = tel_colab;
    }

    public String getDt_nasc_colab() {
        return dt_nasc_colab;
    }

    public void setDt_nasc_colab(String dt_nasc_colab) {
        this.dt_nasc_colab = dt_nasc_colab;
    }

    public String getEndereco_colab() {
        return endereco_colab;
    }

    public void setEndereco_colab(String endereco_colab) {
        this.endereco_colab = endereco_colab;
    }

    public String getEmail_colab() {
        return email_colab;
    }

    public void setEmail_colab(String email_colab) {
        this.email_colab = email_colab;
    }

    public String getSenha_colab() {
        return senha_colab;
    }

    public void setSenha_colab(String senha_colab) {
        this.senha_colab = senha_colab;
    }
}
