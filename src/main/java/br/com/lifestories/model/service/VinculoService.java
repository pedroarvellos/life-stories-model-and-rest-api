package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseVinculoService;
import br.com.lifestories.model.dao.VinculoDAO;
import br.com.lifestories.model.entity.Vinculo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marce
 */
public class VinculoService implements BaseVinculoService {

    @Override
    public void create(Vinculo entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            VinculoDAO dao = new VinculoDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
    }

    @Override
    public Vinculo readById(Long id) throws Exception {
        Vinculo vinculo = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            VinculoDAO dao = new VinculoDAO();
            vinculo = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return vinculo;
    }

    @Override
    public List<Vinculo> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Vinculo> vinculoList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            VinculoDAO dao = new VinculoDAO();
            if (criteria == null) {
                criteria = new HashMap<>();
            }
            vinculoList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return vinculoList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            VinculoDAO dao = new VinculoDAO();
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
    public void update(Vinculo entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            VinculoDAO dao = new VinculoDAO();
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
            VinculoDAO dao = new VinculoDAO();
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
