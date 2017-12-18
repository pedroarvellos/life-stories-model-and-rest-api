package br.com.lifestories.model.dao;

import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.Lingua;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Marcelo
 */
public class IdosoDAO {

    public void create(Connection conn, Idoso entity) throws Exception {
        String sql = "INSERT INTO idoso(ido_usuario_fk, ido_codigo, ido_imagem, ido_instituicao_longa_permanencia_fk) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.setString(++i, entity.getCodigo());
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setLong(++i, entity.getInstituicao().getId());
        ps.execute();
        ps.close();
        this.updateLinguaList(conn, entity);
    }

    public void update(Connection conn, Idoso entity) throws Exception {
        String sql = "UPDATE idoso SET ido_codigo=?, ido_imagem=?, ido_instituicao_longa_permanencia_fk=? WHERE ido_usuario_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getCodigo());
        if (entity.getImagem() != null && !entity.getImagem().isEmpty()) {
            ps.setString(++i, entity.getImagem());
        } else {
            ps.setNull(++i, Types.VARCHAR);
        }
        ps.setLong(++i, entity.getInstituicao().getId());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
        this.updateLinguaList(conn, entity);
    }

    public void readById(Connection conn, Idoso entity) throws Exception {
        String sql = "SELECT * FROM idoso LEFT JOIN usuario ON usu_id = ido_instituicao_longa_permanencia_fk LEFT JOIN localizacao ON ido_instituicao_longa_permanencia_fk = loc_instituicao_longa_permanencia_fk WHERE ido_usuario_fk = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, entity.getId());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entity.setCodigo(rs.getString("ido_codigo"));
            entity.setImagem(rs.getString("ido_imagem"));
            entity.getInstituicao().setId(rs.getLong("ido_instituicao_longa_permanencia_fk"));
            entity.getInstituicao().setNome(rs.getString("usu_nome"));
            entity.getInstituicao().getLocalizacao().setId(rs.getLong("ido_instituicao_longa_permanencia_fk"));
            entity.getInstituicao().getLocalizacao().setLatitude(rs.getBigDecimal("loc_latitude"));
            entity.getInstituicao().getLocalizacao().setLongitude(rs.getBigDecimal("loc_longitude"));
        }

        sql = "SELECT lingua.* FROM idoso LEFT JOIN conhece ON con_idoso_fk = ido_usuario_fk LEFT JOIN lingua ON con_lingua_fk = lin_id WHERE ido_usuario_fk = ?";
        i = 0;
        ps = conn.prepareStatement(sql);
        ps.setLong(++i, entity.getId());
        rs = ps.executeQuery();

        while (rs.next()) {
            if (rs.getLong("lin_id") > 0) {
                Lingua lingua = new Lingua();
                lingua.setId(rs.getLong("lin_id"));
                lingua.setNome(rs.getString("lin_nome"));
                entity.getLinguaList().add(lingua);
            }
        }

        rs.close();
        ps.close();

    }

    private void updateLinguaList(Connection conn, Idoso entity) throws Exception {
        String sql = "DELETE FROM conhece WHERE con_idoso_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();

        sql = "INSERT INTO conhece(con_idoso_fk, con_lingua_fk) VALUES (?, ?);";
        ps = conn.prepareStatement(sql);
        for (Lingua lingua : entity.getLinguaList()) {
            i = 0;
            ps.setLong(++i, entity.getId());
            ps.setLong(++i, lingua.getId());
            ps.execute();
        }
        ps.close();
    }
}
