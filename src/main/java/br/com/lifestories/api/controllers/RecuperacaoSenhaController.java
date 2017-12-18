package br.com.lifestories.api.controllers;

import br.com.lifestories.api.mock.RecuperacaoSenhaMock;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.RecuperacaoSenha;
import br.com.lifestories.model.service.EstudanteService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import br.com.lifestories.model.service.RecuperacaoSenhaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joao Pedro
 */
@RestController
@RequestMapping(value = "/recuperacao")
public class RecuperacaoSenhaController {

    RecuperacaoSenhaService recuperacaoSenhaService = new RecuperacaoSenhaService();
    InstituicaoLongaPermanenciaService insService = new InstituicaoLongaPermanenciaService();
    EstudanteService estService = new EstudanteService();

    @PostMapping
    public ResponseEntity create(@RequestBody RecuperacaoSenhaMock recuperacaoSenhaMock) {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha();
            if (recuperacaoSenhaMock.getTipoUsuario().equals("ins")) {
                criteria.put(UsuarioCriteria.INS_TYPE, true);
                criteria.put(UsuarioCriteria.INSTITUICAO_EMAIL, recuperacaoSenhaMock.getEmail());
                List<InstituicaoLongaPermanencia> instituicaoList = insService.readByCriteria(criteria, null, null);
                if (instituicaoList != null && instituicaoList.size() > 0) {
                    recuperacaoSenha.setUsuario(instituicaoList.get(0));                    
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma instituição com este email!");
                }
            } else if (recuperacaoSenhaMock.getTipoUsuario().equals("est")) {
                criteria.put(UsuarioCriteria.EST_TYPE, true);
                criteria.put(UsuarioCriteria.ESTUDANTE_EMAIL, recuperacaoSenhaMock.getEmail());
                List<Estudante> estudanteList = estService.readByCriteria(criteria, null, null);
                if (estudanteList != null && estudanteList.size() > 0) {
                    recuperacaoSenha.setUsuario(estudanteList.get(0));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum estudante com este email!");
                }
            }
            recuperacaoSenha.setHash(recuperacaoSenhaMock.getHash() + recuperacaoSenha.getUsuario().getId());
            recuperacaoSenha.setAtivo(Boolean.TRUE);
            recuperacaoSenhaService.create(recuperacaoSenha);
            return ResponseEntity.ok(recuperacaoSenha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody RecuperacaoSenhaMock recuperacaoSenhaMock) {
        try {
            RecuperacaoSenha recuperacaoSenha = recuperacaoSenhaService.readById(recuperacaoSenhaMock.getId());
            recuperacaoSenha.setAtivo(recuperacaoSenhaMock.getAtivo());
            recuperacaoSenhaService.update(recuperacaoSenha);
            return ResponseEntity.ok(recuperacaoSenha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity delete(@PathVariable Long idDelete) {
        try {
            recuperacaoSenhaService.delete(idDelete);
            return ResponseEntity.ok("Excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{hash}")
    public ResponseEntity readByHashCode(@PathVariable String hash) {
        try {
            return ResponseEntity.ok(recuperacaoSenhaService.readByHashCode(hash));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
