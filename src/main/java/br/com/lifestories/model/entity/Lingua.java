package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;

public class Lingua extends BaseEntity{
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
