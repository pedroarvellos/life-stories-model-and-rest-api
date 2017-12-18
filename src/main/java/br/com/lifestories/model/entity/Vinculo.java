package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;

public class Vinculo extends BaseEntity {

    private Idoso idoso;
    private Estudante estudante;

    public Vinculo() {
        idoso = new Idoso();
        estudante = new Estudante();
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
}
