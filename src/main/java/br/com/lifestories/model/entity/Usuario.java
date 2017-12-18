package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;

/**
 *
 * @author Marcelo
 */
public abstract class Usuario extends BaseEntity{
    
    private String nome;
    private String senha;
    private String tipo; 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
