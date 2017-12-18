package br.com.lifestories.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class Idoso extends Usuario{
    
    private String codigo;
    private String imagem;
    private InstituicaoLongaPermanencia instituicao;
    private List<Lingua> linguaList;       

    public Idoso() {
        this.linguaList = new ArrayList<>();
        this.instituicao = new InstituicaoLongaPermanencia();
    }
    
    public List<Lingua> getLinguaList() {
        return linguaList;
    }

    public void setLinguaList(List<Lingua> linguaList) {
        this.linguaList = linguaList;
    }    

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public InstituicaoLongaPermanencia getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoLongaPermanencia instituicao) {
        this.instituicao = instituicao;
    }
}
