package br.com.lifestories.api.controllers;

import br.com.lifestories.api.constraints.DefaultConstraints;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.service.IdosoService;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joao
 */
@RestController
@RequestMapping(value = "/instituicoes")
@Api(value="lifestories", description="Classe correspondente aos métodos de acesso relacionados às instituições.")
public class InstituicaoController {
    
    InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
    IdosoService idosoService = new IdosoService();
    
    @GetMapping(value = "/{id}")
    public ResponseEntity readById(@PathVariable Long id) throws Exception{
        try {
            return ResponseEntity.ok(instituicaoService.readById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity readByCriteria(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.INS_TYPE, true);
            if (nome != null && !nome.isEmpty()) {
                criteria.put(UsuarioCriteria.NOME_USUARIO, nome);
            }
            if (status != null && !status.isEmpty()) {
                criteria.put(UsuarioCriteria.INSTITUICAO_STATUS, status);
            }            
            if(limit == null || limit < 0){
                limit = DefaultConstraints.LIMIT_DEFAULT;
            }
            
            Long count = instituicaoService.countByCriteria(criteria);
            PaginaDTO<InstituicaoLongaPermanencia> instituicaoPagina = 
                    new PaginaDTO<>(instituicaoService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(instituicaoPagina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody InstituicaoLongaPermanencia instituicao) throws Exception {
        try {       
            instituicao.setTipo("ains");
            instituicaoService.create(instituicao);
            return ResponseEntity.ok(instituicao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping()
    public ResponseEntity update (@RequestBody InstituicaoLongaPermanencia instituicao) throws Exception{
        try {
            instituicaoService.update(instituicao);
            return ResponseEntity.ok(instituicao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception{
        try {
            instituicaoService.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }        
}
