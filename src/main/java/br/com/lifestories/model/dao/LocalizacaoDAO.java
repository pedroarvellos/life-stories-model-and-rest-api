package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.entity.Localizacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class LocalizacaoDAO implements BaseDAO<Localizacao> {

    @Override
    public void create(Connection conn, Localizacao entity) throws Exception {
        String sql = "INSERT INTO localizacao(loc_instituicao_longa_permanencia_fk, loc_latitude, loc_longitude) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.setBigDecimal(++i, entity.getLatitude());
        ps.setBigDecimal(++i, entity.getLongitude());
        ps.execute();
        ps.close();
    }

    @Override
    public Localizacao readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM localizacao LEFT JOIN usuario ON ";
        sql += "loc_instituicao_longa_permanencia_fk=usu_id WHERE loc_instituicao_longa_permanencia_fk= ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Localizacao entity = new Localizacao();

        if (rs.next()) {
            entity.setId(rs.getLong("loc_instituicao_longa_permanencia_fk"));
            entity.setLatitude(rs.getBigDecimal("loc_latitude"));
            entity.setLongitude(rs.getBigDecimal("loc_longitude"));
        }
        rs.close();
        ps.close();
        return entity;
    }

    @Override
    public List<Localizacao> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connection conn, Localizacao entity) throws Exception {
        String sql = "UPDATE localizacao SET loc_latitude=?, loc_longitude=? WHERE loc_instituicao_longa_permanencia_fk= ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setBigDecimal(++i, entity.getLatitude());
        ps.setBigDecimal(++i, entity.getLongitude());
        ps.setLong(++i, entity.getId());
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
