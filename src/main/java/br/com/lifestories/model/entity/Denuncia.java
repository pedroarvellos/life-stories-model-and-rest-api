package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;

public class Denuncia extends BaseEntity{
    
    String tipo;
    String descricao;
    Estudante estudante;
    Idoso idoso;

    public Denuncia() {
        estudante = new Estudante();
        idoso = new Idoso();
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }
}
