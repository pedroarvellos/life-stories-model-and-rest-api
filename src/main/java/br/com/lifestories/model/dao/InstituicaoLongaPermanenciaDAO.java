package br.com.lifestories.model.dao;

import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Marcelo
 */
public class InstituicaoLongaPermanenciaDAO {

    public void create(Connection conn, InstituicaoLongaPermanencia entity) throws Exception {
        String sql = "INSERT INTO instituicao_longa_permanencia(ins_usuario_fk, ins_telefone, ins_email, ins_registro_legal) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        if (entity.getTelefone() != null && !entity.getTelefone().isEmpty()) {
            ps.setString(++i, entity.getTelefone());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getRegistroLegal());
        ps.execute();
        ps.close();
        
        LocalizacaoDAO dao = new LocalizacaoDAO();
        entity.getLocalizacao().setId(entity.getId());
        dao.create(conn, entity.getLocalizacao());
    }

    public void update(Connection conn, InstituicaoLongaPermanencia entity) throws Exception {
        String sql = "UPDATE instituicao_longa_permanencia SET ins_telefone=?, ins_email=?, ins_registro_legal=? WHERE ins_usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        if (entity.getTelefone() != null && !entity.getTelefone().isEmpty()) {
            ps.setString(++i, entity.getTelefone());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setString(++i, entity.getEmail());
        ps.setString(++i, entity.getRegistroLegal());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
        LocalizacaoDAO dao = new LocalizacaoDAO();
        dao.update(conn, entity.getLocalizacao());
    }

    public void readById(Connection conn, InstituicaoLongaPermanencia entity) throws Exception {
        String sql = "SELECT * FROM instituicao_longa_permanencia LEFT JOIN localizacao ON ins_usuario_fk=loc_instituicao_longa_permanencia_fk WHERE ins_usuario_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getId());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entity.setEmail(rs.getString("ins_email"));
            entity.setTelefone(rs.getString("ins_telefone"));
            entity.setRegistroLegal(rs.getString("ins_registro_legal"));
            entity.getLocalizacao().setId(rs.getLong("loc_instituicao_longa_permanencia_fk"));
            entity.getLocalizacao().setLatitude(rs.getBigDecimal("loc_latitude"));
            entity.getLocalizacao().setLongitude(rs.getBigDecimal("loc_longitude"));
        }

        rs.close();
        ps.close();
    }
}
