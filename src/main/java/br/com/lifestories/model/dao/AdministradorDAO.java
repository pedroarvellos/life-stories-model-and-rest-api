package br.com.lifestories.model.dao;

import br.com.lifestories.model.entity.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marcelo
 */
public class AdministradorDAO {

    public void create(Connection conn, Administrador entity) throws Exception {
        String sql = "INSERT INTO administrador(adm_usuario_fk, adm_email) VALUES (?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.setString(++i, entity.getEmail());
        ps.execute();
        ps.close();
    }

    public void update(Connection conn, Administrador entity) throws Exception {
        String sql = "UPDATE administrador SET adm_email=? WHERE adm_usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getEmail());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }
    
    public void readById(Connection conn, Administrador entity) throws Exception {
        String sql = "SELECT * FROM administrador WHERE adm_usuario_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
       
        int i = 0;
        ps.setLong(++i, entity.getId());
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()) {
            entity.setEmail(rs.getString("adm_email"));
        }
        
        
        rs.close();
        ps.close();
    }
}
