package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseIdosoService;
import br.com.lifestories.model.dao.UsuarioDAO;
import br.com.lifestories.model.entity.Idoso;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class IdosoService implements BaseIdosoService {
    
    @Override
    public void create(Idoso entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }
    
    @Override
    public Idoso readById(Long id) throws Exception {
        Idoso idoso = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            idoso = (Idoso) dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return idoso;
    }
    
    @Override
    public List<Idoso> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Idoso> idosoList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            if(criteria == null){
                criteria = new HashMap<>();
            }
            idosoList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return idosoList;
    }
    
    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
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
    public void update(Idoso entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
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
            UsuarioDAO dao = new UsuarioDAO();
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
