package br.com.lifestories.play;

import br.com.lifestories.model.entity.Denuncia;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.service.DenunciaService;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;

public class TesteDenuncia {

    public static void main(String[] args) throws Exception {
        TesteDenuncia teste = new TesteDenuncia();
        teste.createDenuncia();
    }

    private void createDenuncia() throws Exception {
        Denuncia denuncia = new Denuncia();
        
        denuncia.setDescricao("denuncia");
        denuncia.setTipo("tipo");

        InstituicaoLongaPermanencia instituicao = new InstituicaoLongaPermanencia();
        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicao.setEmail("instituicao2@gmail.com");
        instituicao.setNome("asilo2");
        instituicao.setRegistroLegal("1111-22222");
        instituicao.setSenha("12345");
        instituicao.setTelefone("(35) 96745-2304");
        instituicao.setTipo("ins");
        instituicaoService.create(instituicao);
        
        IdosoService IdosoService = new IdosoService();
        Idoso idoso = new Idoso();
        idoso.setCodigo("1243");
        idoso.setImagem("imagem.png");
        idoso.setInstituicao(instituicao);
        idoso.setNome("jose");
        idoso.setSenha("123");
        idoso.setTipo("ido");
        IdosoService.create(idoso);
        
        EstudanteService EstudanteService = new EstudanteService();
        Estudante estudante = new Estudante();
        estudante.setEmail("estudante2@gmail.com");
        estudante.setImagem("imagem.jpg");
        estudante.setNome("Marcos");
        estudante.setSenha("4321");
        estudante.setTipo("est");
        EstudanteService.create(estudante);
        
        denuncia.setEstudante(estudante);
        denuncia.setIdoso(idoso);
        
        DenunciaService service = new DenunciaService();
        service.create(denuncia);
        
        readByIdDenuncia(denuncia.getId());
    }

    private void readByIdDenuncia(Long id) throws Exception {
        DenunciaService service = new DenunciaService();
        Denuncia denuncia = service.readById(id);

        System.out.println("Id denuncia: " + denuncia.getId());
        System.out.println("Id estudante: " + denuncia.getEstudante().getId());
        System.out.println("Id idoso: " + denuncia.getIdoso().getId());
        System.out.println("Tipo: " + denuncia.getTipo());
        System.out.println("Descrição: " + denuncia.getDescricao());

        updateDenuncia(denuncia);
    }

    private void updateDenuncia(Denuncia denuncia) throws Exception {
        DenunciaService service = new DenunciaService();
        denuncia.setDescricao("descricaoUpdated");
        denuncia.setTipo("tipoUpdated");
       
        service.update(denuncia);
        
        deleteDenuncia(denuncia);
    }
    
    private void deleteDenuncia(Denuncia denuncia) throws Exception {
        DenunciaService service = new DenunciaService();
       
        service.delete(denuncia.getId());
    }
}
