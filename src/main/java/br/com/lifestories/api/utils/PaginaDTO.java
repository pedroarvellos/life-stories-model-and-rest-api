package br.com.lifestories.api.utils;

import br.com.lifestories.model.base.BaseEntity;
import java.util.List;

/**
 *
 * @author Joao Pedro
 */
public class PaginaDTO<E extends BaseEntity> {
    
    private List<E> entityList;
    private Long quantidadeTotalObjetos;

    public PaginaDTO(List<E> entityList, Long quantidadeTotalObjetos) {
        this.entityList = entityList;
        this.quantidadeTotalObjetos = quantidadeTotalObjetos;
    }

    public List<E> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<E> entityList) {
        this.entityList = entityList;
    }

    public Long getQuantidadeTotalObjetos() {
        return quantidadeTotalObjetos;
    }

    public void setQuantidadeTotalObjetos(Long quantidadeTotalObjetos) {
        this.quantidadeTotalObjetos = quantidadeTotalObjetos;
    }
    
    
}
