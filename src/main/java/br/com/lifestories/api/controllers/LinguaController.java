package br.com.lifestories.api.controllers;

import br.com.lifestories.api.constraints.DefaultConstraints;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.LinguaCriteria;
import br.com.lifestories.model.entity.Lingua;
import br.com.lifestories.model.service.LinguaService;
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
@RequestMapping(value = "/linguas")
public class LinguaController {

    LinguaService linguaService = new LinguaService();

    @GetMapping
    public ResponseEntity readByCriteria(@RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "nome", required = false) String nome
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (nome != null && !nome.isEmpty()) {
                criteria.put(LinguaCriteria.NOME_LINGUA, nome);
            }
            if(limit == null || limit < 0){
                limit = DefaultConstraints.LIMIT_DEFAULT;
            }
            Long count = linguaService.countByCriteria(criteria);
            PaginaDTO<Lingua> linguaPagina = new PaginaDTO<>(linguaService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(linguaPagina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/all")
    public ResponseEntity readByCriteriaAll(@RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "nome", required = false) String nome
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (nome != null && !nome.isEmpty()) {
                criteria.put(LinguaCriteria.NOME_LINGUA, nome);
            }
            Long count = linguaService.countByCriteria(criteria);
            PaginaDTO<Lingua> linguaPagina = new PaginaDTO<>(linguaService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(linguaPagina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity readById(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.ok(linguaService.readById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    
    @PostMapping
    public ResponseEntity create(@RequestBody Lingua lingua) throws Exception{
        try {                             
            linguaService.create(lingua);
            return ResponseEntity.ok(lingua);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }  
    
    @DeleteMapping(value = "/{idLingua}")
    public ResponseEntity delete(@PathVariable Long idLingua) throws Exception{
        try {
            linguaService.delete(idLingua);
            return ResponseEntity.ok("Língua excluída com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping
    public ResponseEntity update (@RequestBody Lingua lingua) throws Exception{
        try {
            linguaService.update(lingua);
            return ResponseEntity.ok(lingua);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    
}
