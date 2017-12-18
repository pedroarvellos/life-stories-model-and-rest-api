package br.com.lifestories.api.controllers;

import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.VinculoCriteria;
import br.com.lifestories.model.entity.Vinculo;
import br.com.lifestories.model.service.VinculoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping(value = "/vinculos")
public class VinculoController {

    VinculoService vinculoService = new VinculoService();

    @GetMapping
    public ResponseEntity readByCriteria(
            @RequestParam(value = "idIdoso", required = false) Long idIdoso,
            @RequestParam(value = "idEstudante", required = false) Long idEstudante,
            @RequestParam(value = "limit", required = false) Long limit,
            @RequestParam(value = "offset", required = false) Long offset) {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            
            if(idIdoso != null && idIdoso > 0){
                criteria.put(VinculoCriteria.ID_IDOSO, idIdoso);
            }
            if(idEstudante != null && idEstudante > 0){
                criteria.put(VinculoCriteria.ID_ESTUDANTE, idEstudante);
            }
            
            Long count = vinculoService.countByCriteria(criteria);
            PaginaDTO<Vinculo> paginaVinculo = new PaginaDTO<>(vinculoService.readByCriteria(criteria, limit, offset), count);
            return ResponseEntity.ok(paginaVinculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
