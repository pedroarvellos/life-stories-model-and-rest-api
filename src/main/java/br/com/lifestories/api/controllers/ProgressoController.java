package br.com.lifestories.api.controllers;

import br.com.lifestories.model.criteria.ConversaCriteria;
import br.com.lifestories.model.criteria.DenunciaCriteria;
import br.com.lifestories.model.criteria.VinculoCriteria;
import br.com.lifestories.model.entity.Conversa;
import br.com.lifestories.model.service.ConversaService;
import br.com.lifestories.model.service.DenunciaService;
import br.com.lifestories.model.service.VinculoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcelo
 */
@RestController
@RequestMapping(value = "/progresso")
public class ProgressoController {

    ConversaService conversaService = new ConversaService();
    VinculoService vinculoService = new VinculoService();
    DenunciaService denunciaService = new DenunciaService();

    @GetMapping(value = "/conversas")
    public ResponseEntity conversasById(
            @RequestParam(value = "idEstudante", required = false) Long idEstudante
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (idEstudante != null && idEstudante > 0) {
                criteria.put(ConversaCriteria.ID_ESTUDANTE, idEstudante);
            }
            Long count = conversaService.countByCriteria(criteria);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/vinculos")
    public ResponseEntity vinculosById(
            @RequestParam(value = "idEstudante", required = false) Long idEstudante
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if(idEstudante != null && idEstudante > 0){
                criteria.put(VinculoCriteria.ID_ESTUDANTE, idEstudante);
            }
            Long count = vinculoService.countByCriteria(criteria);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/denuncias")
    public ResponseEntity denunciasById(
            @RequestParam(value = "idEstudante", required = false) Long idEstudante
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if(idEstudante != null && idEstudante > 0){
                criteria.put(DenunciaCriteria.ID_ESTUDANTE, idEstudante);
            }
            criteria.put(DenunciaCriteria.TIPO, "idoso");
            Long count = denunciaService.countByCriteria(criteria);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/avaliacoes")
    public ResponseEntity avaliacaoById(
            @RequestParam(value = "idEstudante", required = false) Long idEstudante
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (idEstudante != null && idEstudante > 0) {
                criteria.put(ConversaCriteria.ID_ESTUDANTE, idEstudante);
            }
            List<Conversa> conversaList = conversaService.readByCriteria(criteria, null, null);
            Integer soma = 0;
            Integer indice = 0;
            for(Conversa conversa : conversaList){
                soma += conversa.getIdosoAvaliacao();
                indice++;
            }
            Integer media = soma / indice;
            
            return ResponseEntity.ok(media);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
