package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseGravacaoService;
import br.com.lifestories.model.dao.GravacaoDAO;
import br.com.lifestories.model.entity.Gravacao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GravacaoService implements BaseGravacaoService{

    @Override
    public void create(Gravacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GravacaoDAO dao = new GravacaoDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }

    @Override
    public Gravacao readById(Long id) throws Exception {
        Gravacao gravacao = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GravacaoDAO dao = new GravacaoDAO();
            gravacao = (Gravacao) dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return gravacao;
    }

    @Override
    public List<Gravacao> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Gravacao> gravacaoList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GravacaoDAO dao = new GravacaoDAO();
            if(criteria == null){
                criteria = new HashMap<>();
            }
            gravacaoList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return gravacaoList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GravacaoDAO dao = new GravacaoDAO();
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
    public void update(Gravacao entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GravacaoDAO dao = new GravacaoDAO();
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
            GravacaoDAO dao = new GravacaoDAO();
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
