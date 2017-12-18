package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseConversaService;
import br.com.lifestories.model.dao.ConversaDAO;
import br.com.lifestories.model.entity.Conversa;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marce
 */
public class ConversaService implements BaseConversaService {

    @Override
    public void create(Conversa entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Conversa readById(Long id) throws Exception {
        Conversa conversa = null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
            conversa = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return conversa;
    }

    @Override
    public List<Conversa> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Conversa> conversaList = new ArrayList<>();
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
            if (criteria == null) {
                criteria = new HashMap<>();
            }
            conversaList = (List) dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
        }
        return conversaList;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        Long count = 0L;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
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
    public void update(Conversa entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            ConversaDAO dao = new ConversaDAO();
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
