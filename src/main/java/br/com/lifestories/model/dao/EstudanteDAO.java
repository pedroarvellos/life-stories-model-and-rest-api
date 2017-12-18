package br.com.lifestories.model.dao;

import br.com.lifestories.model.entity.Estudante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Marcelo
 */
public class EstudanteDAO {

    public void create(Connection conn, Estudante entity) throws Exception {
        String sql = "INSERT INTO estudante(est_usuario_fk, est_imagem, est_email) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setString(++i, entity.getEmail());
        ps.execute();
        ps.close();
    }

    public void update(Connection conn, Estudante entity) throws Exception {
        String sql = "UPDATE estudante SET est_imagem=?, est_email=? WHERE est_usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setString(++i, entity.getEmail());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    public void readById(Connection conn, Estudante entity) throws Exception {
        String sql = "SELECT * FROM estudante WHERE est_usuario_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getId());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entity.setEmail(rs.getString("est_email"));
            entity.setImagem(rs.getString("est_imagem"));
        }

        rs.close();
        ps.close();
    }
}
