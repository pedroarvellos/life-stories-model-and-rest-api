package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseDenunciaService;
import br.com.lifestories.model.dao.DenunciaDAO;
import br.com.lifestories.model.entity.Denuncia;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DenunciaService implements BaseDenunciaService {

    DenunciaDAO dao = new DenunciaDAO();

    @Override
    public void create(Denuncia entity) throws Exception {
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
    public Denuncia readById(Long id) throws Exception {
        Denuncia denuncia = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            denuncia = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return denuncia;
    }

    @Override
    public List<Denuncia> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Denuncia> denunciaList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            if (criteria == null) {
                criteria = new HashMap<>();
            }
            denunciaList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return denunciaList;
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
    public void update(Denuncia entity) throws Exception {
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
        try {;
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
