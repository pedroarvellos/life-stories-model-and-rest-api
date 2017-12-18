package br.com.lifestories.api.controllers;

import br.com.lifestories.api.constraints.DefaultConstraints;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.DenunciaCriteria;
import br.com.lifestories.model.criteria.VinculoCriteria;
import br.com.lifestories.model.entity.Denuncia;
import br.com.lifestories.model.service.DenunciaService;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.validator.internal.cfg.context.DefaultConstraintMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping(value = "/denuncias")
public class DenunciaController {

    DenunciaService denunciaService = new DenunciaService();

    @PostMapping
    public ResponseEntity create(@RequestBody Denuncia denuncia) {
        try {
            denunciaService.create(denuncia);
            return ResponseEntity.ok(denuncia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity readByCriteria(
            @RequestParam(value = "idIdoso", required = false) Long idIdoso,
            @RequestParam(value = "idEstudante", required = false) Long idEstudante,
            @RequestParam(value = "idInstituicao", required = false) Long idInstituicao,
            @RequestParam(value = "nomeEstudante", required = false) String nomeEstudante,
            @RequestParam(value = "nomeIdoso", required = false) String nomeIdoso,            
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset) {
        try {
            
            Map<Long, Object> criteria = new HashMap<>();
            
            if(idIdoso != null && idIdoso > 0){
                criteria.put(DenunciaCriteria.ID_IDOSO, idIdoso);
            }
            if(idEstudante != null && idEstudante > 0){
                criteria.put(DenunciaCriteria.ID_ESTUDANTE, idEstudante);
            }
            if(idInstituicao != null && idInstituicao > 0){
                criteria.put(DenunciaCriteria.ID_INSTITUICAO, idInstituicao);
            }
            if(nomeEstudante != null && !nomeEstudante.isEmpty()){
                criteria.put(DenunciaCriteria.NOME_ESTUDANTE, nomeEstudante);
            }
            if(nomeIdoso != null && !nomeIdoso.isEmpty()){
                criteria.put(DenunciaCriteria.NOME_IDOSO, nomeIdoso);
            }
            if(tipo != null && !tipo.isEmpty()){
                criteria.put(DenunciaCriteria.TIPO, tipo);
            }
            if(limit == null || limit < 0){
                limit = DefaultConstraints.LIMIT_DEFAULT;
            }
            Long count = denunciaService.countByCriteria(criteria);
            PaginaDTO<Denuncia> paginaDenuncia = new PaginaDTO<>(denunciaService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(paginaDenuncia);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/{idDenuncia}")
    public ResponseEntity readByID(@PathVariable Long idDenuncia){
        try {
            return ResponseEntity.ok(denunciaService.readById(idDenuncia));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
