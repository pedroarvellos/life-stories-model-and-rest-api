package br.com.lifestories.model.entity;

/**
 *
 * @author Marcelo
 */
public class InstituicaoLongaPermanencia extends Usuario{
    
    private String telefone;
    private String email;
    private String registroLegal;
    private Localizacao localizacao;

    public InstituicaoLongaPermanencia() {
        localizacao = new Localizacao();
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistroLegal() {
        return registroLegal;
    }

    public void setRegistroLegal(String registroLegal) {
        this.registroLegal = registroLegal;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
