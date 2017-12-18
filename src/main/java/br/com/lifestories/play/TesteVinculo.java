/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.play;

import br.com.lifestories.model.entity.Conversa;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.Vinculo;
import br.com.lifestories.model.service.ConversaService;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import br.com.lifestories.model.service.VinculoService;
import java.sql.Timestamp;

/**
 *
 * @author marce
 */
public class TesteVinculo {
    
    public static void main(String[] args) throws Exception {
        TesteVinculo teste = new TesteVinculo();
        teste.create();
    }
    
    private void create() throws Exception {
        InstituicaoLongaPermanencia instituicao = new InstituicaoLongaPermanencia();
        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicao.setEmail("insttestevinculo@gmail.com");
        instituicao.setNome("vinculoasilo");
        instituicao.setRegistroLegal("111-2222");
        instituicao.setSenha("1234");
        instituicao.setTelefone("(35) 96745-2344");
        instituicao.setTipo("ins");
        instituicaoService.create(instituicao);
        
        Idoso idoso = new Idoso();
        IdosoService idosoService = new IdosoService();
        idoso.setCodigo("1234");
        idoso.setImagem("imagem.png");
        idoso.setNome("idosovinculo");
        idoso.setSenha("123");
        idoso.setInstituicao(instituicao);
        idoso.setTipo("ido");
        idosoService.create(idoso);
        
        Estudante estudante = new Estudante();
        EstudanteService estudanteService = new EstudanteService();
        estudante.setEmail("estudantevinculo@gmail.com");
        estudante.setImagem("imagem.jpg");
        estudante.setNome("Marcosvinculo");
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
        
        conversaService.create(conversa);
        
        readById(conversa.getId());
    }
    
    private void readById(Long id) throws Exception {
        VinculoService service = new VinculoService();
        Vinculo vinculo = service.readById(id);
        update(vinculo);
    }
    
    private void update(Vinculo vinculo) throws Exception {
        VinculoService service = new VinculoService();
        
        Estudante estudante = new Estudante();
        EstudanteService estudanteService = new EstudanteService();
        estudante.setEmail("estudantevinculo2@gmail.com");
        estudante.setImagem("imagem.jpg");
        estudante.setNome("Marcosvinculo2");
        estudante.setSenha("4321");
        estudante.setTipo("est");
        estudanteService.create(estudante);
        
        vinculo.setEstudante(estudante);
        service.update(vinculo);
        
        delete(vinculo.getId());
    }
    
    private void delete(Long id) throws Exception {
        VinculoService service = new VinculoService();
        service.delete(id);
    }
}
