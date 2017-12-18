package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseLinguaService;
import br.com.lifestories.model.dao.LinguaDAO;
import br.com.lifestories.model.entity.Denuncia;
import br.com.lifestories.model.entity.Lingua;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinguaService implements BaseLinguaService{
    LinguaDAO dao = new LinguaDAO();

    @Override
    public void create(Lingua entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }

    @Override
    public Lingua readById(Long id) throws Exception {
        Lingua lingua = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            lingua = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return lingua;
    }

    @Override
    public List<Lingua> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Lingua> linguaList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            if (criteria == null) {
                criteria = new HashMap<>();
            }
            linguaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return linguaList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            if (criteria == null) {
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
    public void update(Lingua entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
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
