package br.com.lifestories.api.controllers;

import br.com.lifestories.api.mock.ConversaMock;
import br.com.lifestories.api.utils.PaginaDTO;
import br.com.lifestories.model.criteria.ConversaCriteria;
import br.com.lifestories.model.entity.Conversa;
import br.com.lifestories.model.service.ConversaService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joao Pedro
 */
@RestController
@RequestMapping(value = "/conversas")
public class ConversaController {

    Map<ConversaMock, Long> conversaMap = new HashMap<>();
    ConversaService conversaService = new ConversaService();

    @GetMapping
    public ResponseEntity readByCriteria(
            @RequestParam(value = "idIdoso", required = false) Long idIdoso,
            @RequestParam(value = "idEstudante", required = false) Long idEstudante,
            @RequestParam(value = "idInstituicao", required = false) Long idInstituicao,
            @RequestParam(value = "nomeIdoso", required = false) String nomeIdoso,
            @RequestParam(value = "nomeEstudante", required = false) String nomeEstudante,
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "limit", required = false) Long limit
    ) throws Exception {
        try {
            Map<Long, Object> criteria = new HashMap<>();
            if (idIdoso != null && idIdoso > 0) {
                criteria.put(ConversaCriteria.ID_IDOSO, idIdoso);
            }

            if (idEstudante != null && idEstudante > 0) {
                criteria.put(ConversaCriteria.ID_ESTUDANTE, idEstudante);
            }
            if (idInstituicao != null && idInstituicao > 0) {
                criteria.put(ConversaCriteria.ID_INSTITUICAO, idInstituicao);
            }
            if (nomeIdoso != null && !nomeIdoso.isEmpty()) {
                criteria.put(ConversaCriteria.NOME_IDOSO, nomeIdoso);
            }

            if (nomeEstudante != null && !nomeEstudante.isEmpty()) {
                criteria.put(ConversaCriteria.NOME_ESTUDANTE, nomeEstudante);
            }
            List<Conversa> conversaList = conversaService.readByCriteria(criteria, limit, offset);
            Long count = conversaService.countByCriteria(criteria);
            PaginaDTO<Conversa> paginaConversa = new PaginaDTO<>(conversaList, count);
            return ResponseEntity.ok(paginaConversa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody ConversaMock conversa) {
        try {

            Iterator<ConversaMock> elements = conversaMap.keySet().iterator();
            boolean encontrei = false;
            if (elements.hasNext()) {
                while (elements.hasNext()) {
                    ConversaMock conversaIt = elements.next();
                    Conversa conversaBD = new Conversa();
                    conversaBD.setEstudante(conversa.getEstudante());
                    conversaBD.setIdoso(conversa.getIdoso());
                    if (conversa.getUsuarioTransmissor().equals("estudante")
                            && conversaIt.getEstudante().getId() == conversa.getEstudante().getId()) {
                        //estudante
                        encontrei = true;
                        Long idConversa = conversaMap.get(conversaIt);
                        if (idConversa != 0) {
                            //atualizar conversa
                            Conversa conversaUpdate = conversaService.readById(idConversa);
                            conversaUpdate.setEstudanteAvaliacao(conversa.getEstudanteAvaliacao());
                            conversaService.update(conversaUpdate);
                            conversaMap.remove(conversaIt);
                        } else {
                            //criar conversa
                            conversaBD.setEstudanteAvaliacao(conversa.getEstudanteAvaliacao());
                            conversaService.create(conversaBD);
                            conversaMap.put(conversaIt, conversaBD.getId());
                        }
                    } else if (conversa.getUsuarioTransmissor().equals("idoso")
                            && conversaIt.getIdoso().getId() == conversa.getIdoso().getId()) {
                        //idoso
                        encontrei = true;
                        Long idConversa = conversaMap.get(conversaIt);
                        if (idConversa != 0) {
                            //atualizar conversa
                            Conversa conversaUpdate = conversaService.readById(idConversa);
                            conversaUpdate.setIdoso(conversa.getIdoso());
                            conversaUpdate.setIdosoAvaliacao(conversa.getIdosoAvaliacao());
                            conversaUpdate.setDataHoraInicio(conversa.getDataHoraInicio());
                            conversaUpdate.setDatahoraFim(conversa.getDatahoraFim());
                            conversaUpdate.setDuracao(conversa.getDuracao());
                            conversaService.update(conversaUpdate);
                            conversaMap.remove(conversaIt);
                        } else {
                            //criar conversa                            
                            conversaBD.setIdosoAvaliacao(conversa.getIdosoAvaliacao());
                            conversaBD.setDataHoraInicio(conversa.getDataHoraInicio());
                            conversaBD.setDatahoraFim(conversa.getDatahoraFim());
                            conversaBD.setDuracao(conversa.getDuracao());
                            conversaService.create(conversaBD);
                            conversaMap.put(conversaIt, conversaBD.getId());
                        }
                    }
                }
            }
            if (!encontrei) {
                ConversaMock conversaMock = new ConversaMock();
                Conversa conversaBD = new Conversa();
                conversaBD.setEstudante(conversa.getEstudante());
                conversaMock.setEstudante(conversa.getEstudante());
                conversaBD.setIdoso(conversa.getIdoso());
                conversaMock.setIdoso(conversa.getIdoso());
                if (conversa.getUsuarioTransmissor().equals("estudante")) {
                    //criar conversa                                    
                    conversaBD.setEstudanteAvaliacao(conversa.getEstudanteAvaliacao());
                    conversaMock.setEstudanteAvaliacao(conversa.getEstudanteAvaliacao());
                    conversaService.create(conversaBD);
                    conversaMap.put(conversaMock, conversaBD.getId());
                } else if (conversa.getUsuarioTransmissor().equals("idoso")) {
                    conversaBD.setIdosoAvaliacao(conversa.getIdosoAvaliacao());
                    conversaBD.setDataHoraInicio(conversa.getDataHoraInicio());
                    conversaBD.setDatahoraFim(conversa.getDatahoraFim());
                    conversaBD.setDuracao(conversa.getDuracao());
                    conversaMock.setIdosoAvaliacao(conversa.getIdosoAvaliacao());
                    conversaService.create(conversaBD);
                    conversaMap.put(conversaMock, conversaBD.getId());
                }
            }
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
