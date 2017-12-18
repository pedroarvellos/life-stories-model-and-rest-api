package br.com.lifestories.api.controllers;

import br.com.lifestories.api.constraints.DefaultConstraints;
import br.com.lifestories.api.utils.ImagemUtils;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.service.IdosoService;
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
 * @author Joao
 */
@RestController
@RequestMapping(value = "/instituicoes/{id}/idosos")
public class IdosoController {
    
    IdosoService idosoService = new IdosoService();
    
    @GetMapping
    public ResponseEntity readByCriteria(@PathVariable Long id, 
            @RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "nome", required = false) String nome
    ) throws Exception{
        try {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UsuarioCriteria.IDOSO_INSTITUICAO, id);
            criteria.put(UsuarioCriteria.IDO_TYPE, true);
            if(nome != null && !nome.isEmpty()){
                criteria.put(UsuarioCriteria.NOME_USUARIO, nome);
            }
            if(limit == null || limit < 0){
                limit = DefaultConstraints.LIMIT_DEFAULT;
            }
            
            Long count = idosoService.countByCriteria(criteria);
            PaginaDTO<Idoso> idosoPagina = 
                    new PaginaDTO<>(idosoService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(idosoPagina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity create(@RequestBody Idoso idoso){
        try {     
            if(idoso.getImagem() == null || idoso.getImagem().isEmpty()){
                idoso.setImagem("https://i.imgur.com/9RAAfJ5.png");
            }
            idoso.setTipo("ido");  
            idosoService.create(idoso);
            return ResponseEntity.ok(idoso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }  
    
    @DeleteMapping(value = "/{idIdoso}")
    public ResponseEntity delete(@PathVariable Long idIdoso){
        try {
            idosoService.delete(idIdoso);
            return ResponseEntity.ok("Idoso excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping
    public ResponseEntity update (@RequestBody Idoso idoso) throws Exception{
        try {
            idosoService.update(idoso);
            return ResponseEntity.ok(idoso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
