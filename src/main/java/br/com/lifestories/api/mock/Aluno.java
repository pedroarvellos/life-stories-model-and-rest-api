/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.api.mock;

import br.com.lifestories.model.entity.Estudante;

/**
 *
 * @author Joao Pedro
 */
public class Aluno {
    
    private String idConexaoSocket;
    private String idRTC;
    private Estudante estudante;
    private String flag;
    private String idConnectionElderly;

    public String getIdConexaoSocket() {
        return idConexaoSocket;
    }

    public void setIdConexaoSocket(String idConexaoSocket) {
        this.idConexaoSocket = idConexaoSocket;
    }

    public String getIdRTC() {
        return idRTC;
    }

    public void setIdRTC(String idRTC) {
        this.idRTC = idRTC;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIdConnectionElderly() {
        return idConnectionElderly;
    }

    public void setIdConnectionElderly(String idConnectionElderly) {
        this.idConnectionElderly = idConnectionElderly;
    }
    
    
    
}
