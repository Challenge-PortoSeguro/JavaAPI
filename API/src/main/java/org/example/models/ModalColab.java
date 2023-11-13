package org.example.models;

public class ModalColab {
    private int id_modal_colab;
    private Modal id_modal;
    private Colaborador id_colab;


    public ModalColab(int id_modal_colab, Modal id_modal, Colaborador id_colab) {
        this.id_modal_colab = id_modal_colab;
        this.id_modal = id_modal;
        this.id_colab = id_colab;
    }
    public ModalColab(){}




    public int getId_modal_colab() {
        return id_modal_colab;
    }

    public void setId_modal_colab(int id_modal_colab) {
        this.id_modal_colab = id_modal_colab;
    }

    public Modal getId_modal() {
        return id_modal;
    }

    public void setId_modal(Modal id_modal) {
        this.id_modal = id_modal;
    }

    public Colaborador getId_colab() {
        return id_colab;
    }

    public void setId_colab(Colaborador id_colab) {
        this.id_colab = id_colab;
    }
}
