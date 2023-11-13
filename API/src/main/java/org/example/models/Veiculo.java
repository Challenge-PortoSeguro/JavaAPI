package org.example.models;

public class Veiculo {
    private int id_veiculo;
    private Medida id_medida;
    private long apolice_veiculo;
    private String modelo_veiculo;
    private String placa_veiculo;
    private int ano_veiculo;
    private int qtd_eixos_veiculo;
    private long renavan_veiculo;
    private String nr_chassi;
    private String tp_chassi;
    private String tp_eixo;


    // Construtor
    public Veiculo(int id_veiculo, Medida id_medida, long apolice_veiculo, String modelo_veiculo, String placa_veiculo, int ano_veiculo, int qtd_eixos_veiculo, long renavan_veiculo, String nr_chassi, String tp_chassi, String tp_eixo) {
        this.id_veiculo = id_veiculo;
        this.id_medida = id_medida;
        this.apolice_veiculo = apolice_veiculo;
        this.modelo_veiculo = modelo_veiculo;
        this.placa_veiculo = placa_veiculo;
        this.ano_veiculo = ano_veiculo;
        this.qtd_eixos_veiculo = qtd_eixos_veiculo;
        this.renavan_veiculo = renavan_veiculo;
        this.nr_chassi = nr_chassi;
        this.tp_chassi = tp_chassi;
        this.tp_eixo = tp_eixo;
    }
    public Veiculo() {}





    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public Medida getId_medida() {
        return id_medida;
    }

    public void setId_medida(Medida id_medida) {
        this.id_medida = id_medida;
    }

    public long getApolice_veiculo() {
        return apolice_veiculo;
    }

    public void setApolice_veiculo(long apolice_veiculo) {
        this.apolice_veiculo = apolice_veiculo;
    }

    public String getModelo_veiculo() {
        return modelo_veiculo;
    }

    public void setModelo_veiculo(String modelo_veiculo) {
        this.modelo_veiculo = modelo_veiculo;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }

    public int getAno_veiculo() {
        return ano_veiculo;
    }

    public void setAno_veiculo(int ano_veiculo) {
        this.ano_veiculo = ano_veiculo;
    }

    public int getQtd_eixos_veiculo() {
        return qtd_eixos_veiculo;
    }

    public void setQtd_eixos_veiculo(int qtd_eixos_veiculo) {
        this.qtd_eixos_veiculo = qtd_eixos_veiculo;
    }

    public long getRenavan_veiculo() {
        return renavan_veiculo;
    }

    public void setRenavan_veiculo(long renavan_veiculo) {
        this.renavan_veiculo = renavan_veiculo;
    }

    public String getNr_chassi() {
        return nr_chassi;
    }

    public void setNr_chassi(String nr_chassi) {
        this.nr_chassi = nr_chassi;
    }

    public String getTp_chassi() {
        return tp_chassi;
    }

    public void setTp_chassi(String tp_chassi) {
        this.tp_chassi = tp_chassi;
    }

    public String getTp_eixo() {
        return tp_eixo;
    }

    public void setTp_eixo(String tp_eixo) {
        this.tp_eixo = tp_eixo;
    }
}
