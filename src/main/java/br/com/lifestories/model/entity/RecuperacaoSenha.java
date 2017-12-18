package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;

/**
 *
 * @author Joao Pedro
 */
public class RecuperacaoSenha extends BaseEntity {
        
    private String hash;
    private Usuario usuario;
    private Boolean ativo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }        

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }   

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
