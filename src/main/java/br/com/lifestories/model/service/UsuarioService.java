package br.com.lifestories.model.service;

import br.com.lifestories.model.ConnectionManager;
import br.com.lifestories.model.base.service.BaseUsuarioService;
import br.com.lifestories.model.dao.UsuarioDAO;
import br.com.lifestories.model.entity.Administrador;
import br.com.lifestories.model.entity.Usuario;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class UsuarioService implements BaseUsuarioService{

    @Override
    public Usuario validarUsuario(String email, String Senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Usuario entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario readById(Long id) throws Exception {
        Usuario usuario =  null;
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            UsuarioDAO dao = new UsuarioDAO();
            usuario = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return usuario;
    }

    @Override
    public List<Usuario> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Usuario entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
