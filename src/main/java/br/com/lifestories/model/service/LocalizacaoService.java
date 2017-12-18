package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.dao.LocalizacaoDAO;
import br.com.lifestories.model.entity.Localizacao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.lifestories.model.base.service.BaseLocalizacaoService;

/**
 *
 * @author Marcelo
 */
public class LocalizacaoService implements BaseLocalizacaoService {
    
    @Override
    public void create(Localizacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }
    
    @Override
    public Localizacao readById(Long id) throws Exception {
        Localizacao localizacao = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            localizacao = (Localizacao) dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return localizacao;
    }
    
    @Override
    public List<Localizacao> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Localizacao> localizacaoList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            if(criteria == null){
                criteria = new HashMap<>();
            }
            localizacaoList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return localizacaoList;
    }
    
    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            if(criteria == null){
                criteria = new HashMap<>();
            }
            count = dao.countByCriteria(conn, criteria);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return count;
    }
    
    @Override
    public void update(Localizacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }
    
    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LocalizacaoDAO dao = new LocalizacaoDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }
    
    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
