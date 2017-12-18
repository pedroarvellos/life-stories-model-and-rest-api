/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseRecuperacaoSenhaService;
import br.com.lifestories.model.dao.RecuperacaoSenhaDAO;
import br.com.lifestories.model.entity.RecuperacaoSenha;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao Pedro
 */
public class RecuperacaoSenhaService implements BaseRecuperacaoSenhaService {

    @Override
    public void create(RecuperacaoSenha entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
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
    public RecuperacaoSenha readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RecuperacaoSenha recuperacaoSenha = null;
        try {
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
            recuperacaoSenha = dao.readById(conn, id);
            conn.close();
        } catch (Exception e) {
            conn.close();
            throw e;
        }
        return recuperacaoSenha;
    }

    @Override
    public List<RecuperacaoSenha> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<RecuperacaoSenha> recuperacaoSenha = null;
        try {
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
            recuperacaoSenha = dao.readByCriteria(conn, criteria, limit, offset);
            conn.close();
        } catch (Exception e) {
            conn.close();
            throw e;
        }
        return recuperacaoSenha;
    }

    public RecuperacaoSenha readByHashCode(String codigo) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        RecuperacaoSenha recuperacaoSenha = null;
        try {
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
            recuperacaoSenha = dao.readByHashCode(conn, codigo);
            conn.close();
        } catch (Exception e) {
            conn.close();
            throw e;
        }
        return recuperacaoSenha;
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(RecuperacaoSenha entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
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
            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
