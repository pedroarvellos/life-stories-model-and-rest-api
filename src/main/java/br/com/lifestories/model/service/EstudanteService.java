package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseEstudanteService;
import br.com.lifestories.model.dao.UsuarioDAO;
import br.com.lifestories.model.entity.Estudante;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class EstudanteService implements BaseEstudanteService {
    
    @Override
    public void create(Estudante entity) throws Exception {
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
    public Estudante readById(Long id) throws Exception {
        Estudante estudante = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            estudante = (Estudante) dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return estudante;
    }
    
    @Override
    public List<Estudante> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Estudante> estudanteList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            if(criteria == null){
                criteria = new HashMap<>();
            }
            estudanteList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return estudanteList;
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
    public void update(Estudante entity) throws Exception {
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
