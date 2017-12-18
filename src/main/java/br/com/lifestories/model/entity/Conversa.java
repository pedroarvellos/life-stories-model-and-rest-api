package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;
import java.sql.Timestamp;

/**
 *
 * @author marcelo
 */
public class Conversa extends BaseEntity{
    private Idoso idoso;
    private Estudante estudante;
    private Integer estudanteAvaliacao;
    private Integer IdosoAvaliacao;
    private Float duracao;
    private Timestamp dataHoraInicio;
    private Timestamp datahoraFim;
    private Gravacao gravacao;
    
    public Conversa() {
        idoso = new Idoso();
        estudante = new Estudante();
        gravacao = new Gravacao();
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

    public Integer getEstudanteAvaliacao() {
        return estudanteAvaliacao;
    }

    public void setEstudanteAvaliacao(Integer estudanteAvaliacao) {
        this.estudanteAvaliacao = estudanteAvaliacao;
    }

    public Integer getIdosoAvaliacao() {
        return IdosoAvaliacao;
    }

    public void setIdosoAvaliacao(Integer IdosoAvaliacao) {
        this.IdosoAvaliacao = IdosoAvaliacao;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Timestamp dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Timestamp getDatahoraFim() {
        return datahoraFim;
    }

    public void setDatahoraFim(Timestamp datahoraFim) {
        this.datahoraFim = datahoraFim;
    }

    public Gravacao getGravacao() {
        return gravacao;
    }

    public void setGravacao(Gravacao gravacao) {
        this.gravacao = gravacao;
    }
}
