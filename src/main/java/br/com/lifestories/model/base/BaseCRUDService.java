/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.model.base;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public interface BaseCRUDService <E extends BaseEntity>{
    
    public void create(E entity) throws Exception;

    public E readById(Long id) throws Exception;

    public List<E> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception;
    
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception;

    public void update(E entity) throws Exception;

    public void delete(Long id) throws Exception;

    public Map<String, String> validate(Map<String, Object> fields) throws Exception;
}
