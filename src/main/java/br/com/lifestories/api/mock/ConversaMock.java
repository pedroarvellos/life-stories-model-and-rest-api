package br.com.lifestories.api.mock;

import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import java.sql.Timestamp;

/**
 *
 * @author Joao Pedro
 */
public class ConversaMock {
    
    private Long id;
    private Idoso idoso;
    private Estudante estudante;
    private Integer estudanteAvaliacao;
    private Integer idosoAvaliacao;
    private Float duracao;
    private Timestamp dataHoraInicio;
    private Timestamp datahoraFim;
    private String usuarioTransmissor;

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

    public String getUsuarioTransmissor() {
        return usuarioTransmissor;
    }

    public void setUsuarioTransmissor(String usuarioTransmissor) {
        this.usuarioTransmissor = usuarioTransmissor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return idosoAvaliacao;
    }

    public void setIdosoAvaliacao(Integer idosoAvaliacao) {
        this.idosoAvaliacao = idosoAvaliacao;
    }
    
}
