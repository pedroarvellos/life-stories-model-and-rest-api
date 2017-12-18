package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.entity.Gravacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class GravacaoDAO implements BaseDAO<Gravacao> {

    @Override
    public void create(Connection conn, Gravacao entity) throws Exception {
        String sql = "INSERT INTO gravacao(gra_audio) VALUES (?)RETURNING gra_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setByte(++i, entity.getAudio());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("gra_id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public Gravacao readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM gravacao WHERE gra_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Gravacao entity = null;

        if (rs.next()) {
            entity = new Gravacao();
            entity.setId(rs.getLong("gra_id"));
            entity.setAudio(rs.getByte("gra_audio"));
        }
        rs.close();
        ps.close();
        return entity;
    }

    @Override
    public List<Gravacao> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
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
    public void update(Connection conn, Gravacao entity) throws Exception {
        String sql = "UPDATE gravacao SET gra_audio=? WHERE gra_id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setByte(++i, entity.getAudio());
        ps.setLong(++i, entity.getId());
        
        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String query = "DELETE FROM gravacao WHERE gra_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
