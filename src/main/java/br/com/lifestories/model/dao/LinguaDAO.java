package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.criteria.LinguaCriteria;
import br.com.lifestories.model.entity.Lingua;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinguaDAO implements BaseDAO<Lingua> {

    @Override
    public void create(Connection conn, Lingua entity) throws Exception {
        String sql = "INSERT INTO lingua(lin_nome) VALUES (?)RETURNING lin_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("lin_id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public Lingua readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * FROM lingua WHERE lin_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Lingua entity = null;

        if (rs.next()) {
            entity = new Lingua();
            entity.setId(rs.getLong("lin_id"));
            entity.setNome(rs.getString("lin_nome"));
        }

        rs.close();
        ps.close();
        return entity;
    }

    @Override
    public List<Lingua> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Lingua> linguaList = new ArrayList<>();
        String sql = "SELECT * FROM lingua WHERE 1=1";

        sql += this.applyCriteria(criteria);

        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }

        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);

        Lingua entity = null;

        while (rs.next()) {
            entity = new Lingua();
            entity.setId(rs.getLong("lin_id"));
            entity.setNome(rs.getString("lin_nome"));

            linguaList.add(entity);
        }

        rs.close();
        ps.close();

        return linguaList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = " ";
        String nomeLingua = (String) criteria.get(LinguaCriteria.NOME_LINGUA);
        if (nomeLingua != null && !nomeLingua.isEmpty()) {
            sql += " AND lin_nome ILIKE '%" + nomeLingua + "%'";
        }

        return sql;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT COUNT(*) count FROM lingua WHERE 1=1 ";
        sql += this.applyCriteria(criteria);

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);

        Long count = null;
        while (rs.next()) {
            count = rs.getLong("count");
        }

        rs.close();
        s.close();

        return count;
    }

    @Override
    public void update(Connection conn, Lingua entity) throws Exception {
        String sql = "UPDATE lingua SET lin_nome=? WHERE lin_id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());                
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String query = "DELETE FROM lingua WHERE lin_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
