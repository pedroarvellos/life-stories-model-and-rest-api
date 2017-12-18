package br.com.lifestories.model.entity;

/**
 *
 * @author Marcelo
 */
public class Estudante extends Usuario{
    
    private String imagem;
    private String email;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
