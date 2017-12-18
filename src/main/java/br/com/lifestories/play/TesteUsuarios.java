package br.com.lifestories.play;

import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Administrador;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.service.AdministradorService;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class TesteUsuarios {

    public static void main(String[] args) throws Exception {
        TesteUsuarios teste = new TesteUsuarios();
        //teste.createUsuarios();
        //teste.readUsuarioById(7L, 5L, 2L, 1L);
        Map<Long, Object> criteria = new HashMap<>();
        teste.readUsuarioByCriteria(criteria, null, null);
    }

    private void createUsuarios() throws Exception {
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
        idoso.setInstituicao(instituicao);
        idoso.setNome("jose");
        idoso.setSenha("123");
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

        Administrador adm = new Administrador();
        AdministradorService admService = new AdministradorService();
        adm.setEmail("adm@email");
        adm.setNome("adm");
        adm.setSenha("1234");
        adm.setTipo("adm");
        admService.create(adm);

        this.readUsuarioById(estudante.getId(), idoso.getId(), instituicao.getId(), adm.getId());

        Map<Long, Object> criteria = new HashMap();
        this.readUsuarioByCriteria(criteria, null, null);

        updateUsuarios(estudante, idoso, instituicao, adm);
    }

    private void updateUsuarios(Estudante estudante, Idoso idoso, InstituicaoLongaPermanencia instituicao, Administrador adm) throws Exception {

        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicao.setEmail("ALTinstituicao@gmail.com");
        instituicao.setNome("ALTasilo");
        instituicao.setRegistroLegal("ALT111-2222");
        instituicao.setSenha("ALT1234");
        instituicao.setTelefone("ALT(35) 96745-2344");
        instituicao.setTipo("ins");
        instituicaoService.update(instituicao);

        IdosoService idosoService = new IdosoService();
        idoso.setCodigo("ALT1234");
        idoso.setImagem("ALTimagem.png");
        idoso.setInstituicao(instituicao);
        idoso.setNome("ALTjose");
        idoso.setSenha("ALT123");
        idoso.setTipo("ido");
        idosoService.update(idoso);

        EstudanteService estudanteService = new EstudanteService();
        estudante.setEmail("ALTestudante@gmail.com");
        estudante.setImagem("ALTimagem.jpg");
        estudante.setNome("ALTMarcos");
        estudante.setSenha("ALT4321");
        estudante.setTipo("est");
        estudanteService.update(estudante);

        AdministradorService admService = new AdministradorService();
        adm.setEmail("ALTadm@email");
        adm.setNome("ALTadm");
        adm.setSenha("ALT1234");
        adm.setTipo("adm");
        admService.update(adm);

        deleteUsuarios(estudante, idoso, instituicao, adm);
    }

    private void deleteUsuarios(Estudante estudante, Idoso idoso, InstituicaoLongaPermanencia instituicao, Administrador adm) throws Exception {
        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicaoService.delete(instituicao.getId());

        IdosoService idosoService = new IdosoService();
        idosoService.delete(idoso.getId());

        EstudanteService estudanteService = new EstudanteService();
        estudanteService.delete(estudante.getId());

        AdministradorService admService = new AdministradorService();
        admService.delete(adm.getId());

    }

    private void readUsuarioById(Long estudanteId, Long idosoId, Long instId, Long admId) throws Exception {
        AdministradorService admService = new AdministradorService();
        EstudanteService estService = new EstudanteService();
        IdosoService idoService = new IdosoService();
        InstituicaoLongaPermanenciaService insService = new InstituicaoLongaPermanenciaService();

        Administrador administrador = admService.readById(admId);
        Estudante estudante = estService.readById(estudanteId);
        Idoso idoso = idoService.readById(idosoId);
        InstituicaoLongaPermanencia instituicao = insService.readById(instId);
    }

    private void readUsuarioByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        AdministradorService admService = new AdministradorService();
        EstudanteService estService = new EstudanteService();
        IdosoService idoService = new IdosoService();
        InstituicaoLongaPermanenciaService insService = new InstituicaoLongaPermanenciaService();

        criteria.clear();
        criteria.put(UsuarioCriteria.ADM_TYPE, true);
        List<Administrador> admList = admService.readByCriteria(criteria, limit, offset);

        criteria.clear();
        criteria.put(UsuarioCriteria.EST_TYPE, true);
        List<Estudante> estList = estService.readByCriteria(criteria, limit, offset);

        criteria.clear();
        criteria.put(UsuarioCriteria.IDO_TYPE, true);
        List<Idoso> idoList = idoService.readByCriteria(criteria, limit, offset);

        criteria.clear();
        criteria.put(UsuarioCriteria.INS_TYPE, true);
        List<InstituicaoLongaPermanencia> insList = insService.readByCriteria(criteria, limit, offset);
    }
}
