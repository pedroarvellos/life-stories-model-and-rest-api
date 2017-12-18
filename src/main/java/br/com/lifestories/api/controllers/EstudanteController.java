/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.api.controllers;

import br.com.lifestories.api.constraints.DefaultConstraints;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.service.EstudanteService;
import io.swagger.annotations.Api;
import java.util.HashMap;
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
 * @author Joao Pedro
 */
@RestController
@RequestMapping(value = "/estudantes")
@Api(value="lifestories", description="Classe correspondente aos métodos de acesso relacionados aos estudantes.")
public class EstudanteController {

    EstudanteService es = new EstudanteService();
    
    @GetMapping(value = "/{id}")
    public ResponseEntity readById(@PathVariable Long id) throws Exception{
        try {
            return ResponseEntity.ok(es.readById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity readByCriteria(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "limit", required = false) Long limit
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.EST_TYPE, true);
            if (nome != null && !nome.isEmpty()) {
                criteria.put(UsuarioCriteria.NOME_USUARIO, nome);
            }
            if(limit == null || limit < 0){
                limit = DefaultConstraints.LIMIT_DEFAULT;
            }
            Long count = es.countByCriteria(criteria);
            PaginaDTO<Estudante> estudantePagina = new PaginaDTO<>(es.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(estudantePagina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Estudante estudante) throws Exception {
        try {    
            if(estudante.getImagem() == null || estudante.getImagem().isEmpty()){
                estudante.setImagem("https://i.imgur.com/9RAAfJ5.png");
            }
            estudante.setTipo("est");
            es.create(estudante);
            return ResponseEntity.ok(estudante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping
    public ResponseEntity update (@RequestBody Estudante estudante) throws Exception{
        try {
            es.update(estudante);
            return ResponseEntity.ok(estudante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception{
        try {
            es.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
