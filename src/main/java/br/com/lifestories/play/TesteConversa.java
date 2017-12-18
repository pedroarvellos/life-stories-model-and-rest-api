package br.com.lifestories.play;

import br.com.lifestories.model.entity.Conversa;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.service.ConversaService;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import java.sql.Timestamp;

/**
 *
 * @author marcelo
 */
public class TesteConversa {

    public static void main(String[] args) throws Exception {
        TesteConversa teste = new TesteConversa();
        teste.create();
    }

    private void create() throws Exception {
        InstituicaoLongaPermanencia instituicao = new InstituicaoLongaPermanencia();
        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicao.setEmail("instituicao@gmail.com");
        instituicao.setNome("asilo");
        instituicao.setRegistroLegal("111-2222");
        instituicao.setSenha("1234");
        instituicao.setTelefone("(35) 96745-2344");
        instituicao.setTipo("ins");
        instituicaoService.create(instituicao);
        
        Idoso idoso = new Idoso();
        IdosoService idosoService = new IdosoService();
        idoso.setCodigo("1234");
        idoso.setImagem("imagem.png");
        idoso.setNome("idoso");
        idoso.setSenha("123");
        idoso.setInstituicao(instituicao);
        idoso.setTipo("ido");
        idosoService.create(idoso);

        Estudante estudante = new Estudante();
        EstudanteService estudanteService = new EstudanteService();
        estudante.setEmail("estudante@gmail.com");
        estudante.setImagem("imagem.jpg");
        estudante.setNome("Marcos");
        estudante.setSenha("4321");
        estudante.setTipo("est");
        estudanteService.create(estudante);

        Conversa conversa = new Conversa();
        conversa.setEstudante(estudante);
        conversa.setIdoso(idoso);
        Timestamp timeInicio = new Timestamp(100);
        Timestamp timeFim = new Timestamp(1500);

        conversa.setDataHoraInicio(timeInicio);
        conversa.setDatahoraFim(timeFim);
        conversa.setDuracao(100F);
        conversa.setEstudanteAvaliacao(5);
        conversa.setIdosoAvaliacao(5);
        ConversaService conversaService = new ConversaService();
        conversaService.create(conversa);
        
        readById(conversa.getId());
    }
    
    private void readById(Long id) throws Exception{
        ConversaService conversaService = new ConversaService();
        Conversa conversa = conversaService.readById(id);
        
        conversa.setDuracao(1000F);
        conversa.setEstudanteAvaliacao(1);
        conversa.setIdosoAvaliacao(1);
        update(conversa);
    }
    
    private void update(Conversa conversa) throws Exception{
        ConversaService conversaService = new ConversaService();
        conversaService.update(conversa);
        
        delete(conversa.getId());
    }

    private void delete(Long id) throws Exception {
        ConversaService conversaService = new ConversaService();
        conversaService.delete(id);
    }
    
}
