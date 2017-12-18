/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.api.mock;

import br.com.lifestories.model.entity.Idoso;
import java.util.Objects;

/**
 *
 * @author Joao Pedro
 */
public class Professor {
    
    private String idConexaoSocket;
    private String idRTC;
    private Idoso idoso;
    private String flag;

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

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idConexaoSocket);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.idConexaoSocket, other.idConexaoSocket)) {
            return false;
        }
        return true;
    }
    
    
    
}
