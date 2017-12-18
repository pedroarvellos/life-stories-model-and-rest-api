package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.criteria.DenunciaCriteria;
import br.com.lifestories.model.criteria.VinculoCriteria;
import br.com.lifestories.model.entity.Denuncia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DenunciaDAO implements BaseDAO<Denuncia> {

    @Override
    public void create(Connection conn, Denuncia entity) throws Exception {
        String sql = "INSERT INTO denuncia(den_estudante_fk, den_idoso_fk, den_tipo, den_descricao) VALUES (?, ?, ?, ?) RETURNING den_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getEstudante().getId());
        ps.setLong(++i, entity.getIdoso().getId());
        ps.setString(++i, entity.getTipo());
        ps.setString(++i, entity.getDescricao());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("den_id"));
        }

        ps.close();
    }

    @Override
    public Denuncia readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT  den_id, den_tipo, den_descricao, i.usu_id ido_id, ";
        sql += "e.usu_id est_id ,i.usu_nome ido_nome, e.usu_nome est_nome, ";
        sql += "i.usu_tipo ido_tipo, e.usu_tipo est_tipo, ido_codigo, ";
        sql += "ido_imagem, est_imagem, est_email FROM denuncia ";
        sql += "LEFT JOIN idoso ON den_idoso_fk = ido_usuario_fk ";
        sql += "LEFT JOIN usuario i ON i.usu_id = idoso.ido_usuario_fk ";
        sql += "LEFT JOIN estudante ON den_estudante_fk = est_usuario_fk ";
        sql += "LEFT JOIN usuario e ON e.usu_id = estudante.est_usuario_fk WHERE den_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Denuncia entity = new Denuncia();

        if (rs.next()) {
            entity.setId(rs.getLong("den_id"));
            entity.setTipo(rs.getString("den_tipo"));
            entity.setDescricao(rs.getString("den_descricao"));
            entity.getIdoso().setId(rs.getLong("ido_id"));
            entity.getIdoso().setNome(rs.getString("ido_nome"));
            entity.getIdoso().setTipo(rs.getString("ido_tipo"));
            entity.getIdoso().setCodigo(rs.getString("ido_codigo"));
            entity.getIdoso().setImagem(rs.getString("ido_imagem"));
            entity.getEstudante().setId(rs.getLong("est_id"));
            entity.getEstudante().setNome(rs.getString("est_nome"));
            entity.getEstudante().setTipo(rs.getString("est_tipo"));
            entity.getEstudante().setEmail(rs.getString("est_email"));
            entity.getEstudante().setImagem(rs.getString("est_imagem"));
        }
        rs.close();
        ps.close();

        return entity;
    }

    @Override
    public List<Denuncia> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Denuncia> denunciaList = new ArrayList<>();
        String sql = "SELECT  den_id, den_tipo, den_descricao, i.usu_id ido_usuario_fk, \n"
                + "e.usu_id est_usuario_fk ,i.usu_nome ido_nome, e.usu_nome est_nome, \n"
                + "i.usu_tipo ido_tipo, e.usu_tipo est_tipo, ido_codigo, \n"
                + "ido_imagem, est_imagem, est_email FROM denuncia \n"
                + "LEFT JOIN idoso ON den_idoso_fk = ido_usuario_fk \n"
                + "LEFT JOIN usuario i ON i.usu_id = idoso.ido_usuario_fk \n"
                + "LEFT JOIN estudante ON den_estudante_fk = est_usuario_fk \n"
                + "LEFT JOIN usuario e ON e.usu_id = estudante.est_usuario_fk WHERE 1=1 ";

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
            Denuncia entity = new Denuncia();

            entity.setId(rs.getLong("den_id"));
            entity.setTipo(rs.getString("den_tipo"));
            entity.setDescricao(rs.getString("den_descricao"));
            entity.getIdoso().setId(rs.getLong("ido_usuario_fk"));
            entity.getIdoso().setNome(rs.getString("ido_nome"));
            entity.getIdoso().setTipo(rs.getString("ido_tipo"));
            entity.getIdoso().setCodigo(rs.getString("ido_codigo"));
            entity.getIdoso().setImagem(rs.getString("ido_imagem"));
            entity.getEstudante().setId(rs.getLong("est_usuario_fk"));
            entity.getEstudante().setNome(rs.getString("est_nome"));
            entity.getEstudante().setTipo(rs.getString("est_tipo"));
            entity.getEstudante().setEmail(rs.getString("est_email"));
            entity.getEstudante().setImagem(rs.getString("est_imagem"));

            denunciaList.add(entity);
        }

        rs.close();
        s.close();
        return denunciaList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = " ";

        Long idIdoso = (Long) criteria.get(DenunciaCriteria.ID_IDOSO);
        if (idIdoso != null && idIdoso > 0) {
            sql += " and ido_usuario_fk = " + idIdoso;
        }
        Long idEstudante = (Long) criteria.get(DenunciaCriteria.ID_ESTUDANTE);
        if (idEstudante != null && idEstudante > 0) {
            sql += " and est_usuario_fk = " + idEstudante;
        }
        Long idInstituicao = (Long) criteria.get(DenunciaCriteria.ID_INSTITUICAO);
        if (idInstituicao != null && idInstituicao > 0) {
            sql += " and ido_instituicao_longa_permanencia_fk = " + idInstituicao;
        }
        String nomeEstudante = (String) criteria.get(DenunciaCriteria.NOME_ESTUDANTE);
        if (nomeEstudante != null && !nomeEstudante.isEmpty()) {
            sql += " and e.usu_nome ILIKE '%" + nomeEstudante + "%'";
        }
        String nomeIdoso = (String) criteria.get(DenunciaCriteria.NOME_IDOSO);
        if (nomeIdoso != null && !nomeIdoso.isEmpty()) {
            sql += " and i.usu_nome ILIKE '%" + nomeIdoso + "%'";
        }
        String tipo = (String) criteria.get(DenunciaCriteria.TIPO);
        if (tipo != null && !tipo.isEmpty()) {
            sql += " and den_tipo = '" + tipo +"'";
        }

        return sql;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT  COUNT(*) count FROM denuncia \n"
                + "LEFT JOIN idoso ON den_idoso_fk = ido_usuario_fk \n"
                + "LEFT JOIN usuario i ON i.usu_id = idoso.ido_usuario_fk \n"
                + "LEFT JOIN estudante ON den_estudante_fk = est_usuario_fk \n"
                + "LEFT JOIN usuario e ON e.usu_id = estudante.est_usuario_fk WHERE 1=1 ";
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
    public void update(Connection conn, Denuncia entity) throws Exception {
        String sql = "UPDATE denuncia SET den_estudante_fk=?, den_idoso_fk=?, den_tipo=?, den_descricao=? WHERE den_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getEstudante().getId());
        ps.setLong(++i, entity.getIdoso().getId());
        ps.setString(++i, entity.getTipo());
        ps.setString(++i, entity.getDescricao());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM denuncia WHERE den_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }
}
