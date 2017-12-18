package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.criteria.VinculoCriteria;
import br.com.lifestories.model.entity.Vinculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VinculoDAO implements BaseDAO<Vinculo> {

    @Override
    public void create(Connection conn, Vinculo entity) throws Exception {
        String sql = "INSERT INTO vinculo(vin_conversa_fk, vin_estudante_fk, vin_idoso_fk) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getId());
        ps.setLong(++i, entity.getEstudante().getId());
        ps.setLong(++i, entity.getIdoso().getId());

        ps.execute();
        ps.close();
    }

    @Override
    public Vinculo readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT i.usu_nome ido_nome, i.usu_tipo ido_tipo, e.usu_nome est_nome, e.usu_tipo est_tipo, *"
                + " FROM vinculo LEFT JOIN estudante ON est_usuario_fk=vin_estudante_fk "
                + "LEFT JOIN usuario e ON e.usu_id=vin_estudante_fk "
                + "LEFT JOIN idoso ON ido_usuario_fk=vin_idoso_fk "
                + "LEFT JOIN usuario i ON vin_idoso_fk=i.usu_id WHERE vin_conversa_fk= ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Vinculo entity = null;

        if (rs.next()) {
            entity = new Vinculo();
            entity.setId(rs.getLong("vin_conversa_fk"));

            entity.getEstudante().setId(rs.getLong("est_usuario_fk"));
            entity.getEstudante().setNome(rs.getString("est_nome"));
            entity.getEstudante().setTipo(rs.getString("est_tipo"));
            entity.getEstudante().setImagem(rs.getString("est_imagem"));

            entity.getIdoso().setId(rs.getLong("ido_usuario_fk"));
            entity.getIdoso().setNome(rs.getString("ido_nome"));
            entity.getIdoso().setTipo(rs.getString("ido_tipo"));
            entity.getIdoso().setImagem(rs.getString("ido_imagem"));

        }
        rs.close();
        ps.close();
        return entity;
    }

    @Override
    public List<Vinculo> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Vinculo> vinculoList = new ArrayList<>();
        String sql = "SELECT i.usu_nome ido_nome, i.usu_tipo ido_tipo, e.usu_nome est_nome, e.usu_tipo est_tipo, *"
                + " FROM vinculo LEFT JOIN estudante ON est_usuario_fk=vin_estudante_fk "
                + "LEFT JOIN usuario e ON e.usu_id=vin_estudante_fk "
                + "LEFT JOIN idoso ON ido_usuario_fk=vin_idoso_fk "
                + "LEFT JOIN usuario i ON vin_idoso_fk=i.usu_id WHERE 1=1 ";
        sql += this.applyCriteria(criteria);

        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            Vinculo entity = new Vinculo();
            entity.setId(rs.getLong("vin_conversa_fk"));

            entity.getEstudante().setId(rs.getLong("est_usuario_fk"));
            entity.getEstudante().setNome(rs.getString("est_nome"));
            entity.getEstudante().setTipo(rs.getString("est_tipo"));
            entity.getEstudante().setImagem(rs.getString("est_imagem"));

            entity.getIdoso().setId(rs.getLong("ido_usuario_fk"));
            entity.getIdoso().setNome(rs.getString("ido_nome"));
            entity.getIdoso().setTipo(rs.getString("ido_tipo"));
            entity.getIdoso().setImagem(rs.getString("ido_imagem"));

            vinculoList.add(entity);
        }

        rs.close();
        s.close();
        return vinculoList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = " ";

        Long idIdoso = (Long) criteria.get(VinculoCriteria.ID_IDOSO);
        if (idIdoso != null && idIdoso > 0) {
            sql += " and ido_usuario_fk = " + idIdoso;
        }
        Long idEstudante = (Long) criteria.get(VinculoCriteria.ID_ESTUDANTE);
        if (idEstudante != null && idEstudante > 0) {
            sql += " and est_usuario_fk = " + idEstudante;
        }

        return sql;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT COUNT(*) count\n"
                + "FROM vinculo LEFT JOIN estudante ON est_usuario_fk=vin_estudante_fk \n"
                + "LEFT JOIN usuario e ON e.usu_id=vin_estudante_fk \n"
                + "LEFT JOIN idoso ON ido_usuario_fk=vin_idoso_fk \n"
                + "LEFT JOIN usuario i ON vin_idoso_fk=i.usu_id WHERE 1=1 ";
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
    public void update(Connection conn, Vinculo entity) throws Exception {
        String sql = "UPDATE vinculo SET vin_estudante_fk=?, vin_idoso_fk=? WHERE vin_conversa_fk=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getEstudante().getId());
        ps.setLong(++i, entity.getIdoso().getId());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String query = "DELETE FROM vinculo WHERE vin_conversa_fk=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
