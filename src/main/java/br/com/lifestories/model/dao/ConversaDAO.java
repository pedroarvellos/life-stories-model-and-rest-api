/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.criteria.ConversaCriteria;
import br.com.lifestories.model.entity.Conversa;
import br.com.lifestories.model.entity.Vinculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marce
 */
public class ConversaDAO implements BaseDAO<Conversa> {

    @Override
    public void create(Connection conn, Conversa entity) throws Exception {
        String sql = "INSERT INTO conversa(conve_estudante_fk, conve_idoso_fk, conve_estudante_avaliacao,"
                + " conve_idoso_avaliacao, conve_data_hora_inicio, conve_data_hora_fim, conve_duracao, conve_gravacao_fk)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)RETURNING conve_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getEstudante().getId());
        if (entity.getIdoso() != null) {
            if (entity.getIdoso().getId() > 0) {
                ps.setLong(++i, entity.getIdoso().getId());
            } else {
                ps.setNull(++i, Types.BIGINT);
            }
        } else {
            ps.setNull(++i, Types.BIGINT);
        }
        if (entity.getEstudanteAvaliacao() != null) {
            ps.setInt(++i, entity.getEstudanteAvaliacao());
        } else {
            ps.setNull(++i, Types.INTEGER);
        }
        if (entity.getIdosoAvaliacao() != null) {
            ps.setInt(++i, entity.getIdosoAvaliacao());
        } else {
            ps.setNull(++i, Types.INTEGER);
        }
        if (entity.getDataHoraInicio() != null) {
            ps.setTimestamp(++i, entity.getDataHoraInicio());
        } else {
            ps.setNull(++i, Types.TIMESTAMP);
        }
        if (entity.getDatahoraFim() != null) {
            ps.setTimestamp(++i, entity.getDatahoraFim());
        } else {
            ps.setNull(++i, Types.TIMESTAMP);
        }
        if (entity.getDuracao() != null) {
            ps.setFloat(++i, entity.getDuracao());
        } else {
            ps.setNull(++i, Types.FLOAT);
        }
        if (entity.getGravacao().getAudio() != null && entity.getGravacao().getAudio().longValue() != 0) {
            ps.setLong(++i, entity.getGravacao().getId());
        } else {
            ps.setNull(++i, Types.BIGINT);
        }

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("conve_id"));
        }

        sql = "SELECT count(*) count FROM vinculo WHERE vin_idoso_fk=? AND vin_estudante_fk=?;";
        ps = conn.prepareStatement(sql);
        i = 0;
        ps.setLong(++i, entity.getIdoso().getId());
        ps.setLong(++i, entity.getEstudante().getId());

        rs = ps.executeQuery();
        Long count = 0L;
        if (rs.next()) {
            count = rs.getLong("count");
        }
        rs.close();
        ps.close();

        if (count.equals(0L)) {
            VinculoDAO dao = new VinculoDAO();
            Vinculo vinculo = new Vinculo();
            vinculo.setId(entity.getId());
            vinculo.setEstudante(entity.getEstudante());
            vinculo.setIdoso(entity.getIdoso());
            dao.create(conn, vinculo);
        }
    }

    @Override
    public Conversa readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT i.usu_nome ido_nome, i.usu_tipo ido_tipo, e.usu_nome est_nome, e.usu_tipo est_tipo, * "
                + "FROM conversa LEFT JOIN idoso ON conve_idoso_fk=ido_usuario_fk "
                + "LEFT JOIN usuario i ON i.usu_id=ido_usuario_fk "
                + "LEFT JOIN estudante ON conve_estudante_fk=est_usuario_fk "
                + "LEFT JOIN usuario e ON e.usu_id=est_usuario_fk WHERE conve_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Conversa entity = null;

        if (rs.next()) {
            entity = new Conversa();
            entity.setId(rs.getLong("conve_id"));
            entity.setEstudanteAvaliacao(rs.getInt("conve_estudante_avaliacao"));
            entity.setIdosoAvaliacao(rs.getInt("conve_idoso_avaliacao"));
            entity.setDataHoraInicio(rs.getTimestamp("conve_data_hora_inicio"));
            entity.setDatahoraFim(rs.getTimestamp("conve_data_hora_fim"));
            entity.setDuracao(rs.getFloat("conve_duracao"));
            entity.getGravacao().setId(rs.getLong("conve_gravacao_fk"));

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
    public List<Conversa> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        List<Conversa> conversaList = new ArrayList<>();
        String sql = "SELECT i.usu_nome ido_nome, i.usu_tipo ido_tipo, e.usu_nome est_nome, e.usu_tipo est_tipo, * "
                + "FROM conversa LEFT JOIN idoso ON conve_idoso_fk=ido_usuario_fk "
                + "LEFT JOIN usuario i ON i.usu_id=ido_usuario_fk "
                + "LEFT JOIN estudante ON conve_estudante_fk=est_usuario_fk "
                + "LEFT JOIN usuario e ON e.usu_id=est_usuario_fk WHERE 1=1 ";
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
            Conversa entity = new Conversa();

            entity.setId(rs.getLong("conve_id"));
            entity.setEstudanteAvaliacao(rs.getInt("conve_estudante_avaliacao"));
            entity.setIdosoAvaliacao(rs.getInt("conve_idoso_avaliacao"));
            entity.setDataHoraInicio(rs.getTimestamp("conve_data_hora_inicio"));
            entity.setDatahoraFim(rs.getTimestamp("conve_data_hora_fim"));
            entity.setDuracao(rs.getFloat("conve_duracao"));
            entity.getGravacao().setId(rs.getLong("conve_gravacao_fk"));

            entity.getEstudante().setId(rs.getLong("est_usuario_fk"));
            entity.getEstudante().setNome(rs.getString("est_nome"));
            entity.getEstudante().setTipo(rs.getString("est_tipo"));
            entity.getEstudante().setImagem(rs.getString("est_imagem"));

            entity.getIdoso().setId(rs.getLong("ido_usuario_fk"));
            entity.getIdoso().setNome(rs.getString("ido_nome"));
            entity.getIdoso().setTipo(rs.getString("ido_tipo"));
            entity.getIdoso().setImagem(rs.getString("ido_imagem"));

            conversaList.add(entity);
        }

        rs.close();
        s.close();
        return conversaList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception {
        String sql = " ";

        Long idIdoso = (Long) criteria.get(ConversaCriteria.ID_IDOSO);
        if (idIdoso != null && idIdoso > 0) {
            sql += " and ido_usuario_fk = " + idIdoso;
        }
        Long idEstudante = (Long) criteria.get(ConversaCriteria.ID_ESTUDANTE);
        if (idEstudante != null && idEstudante > 0) {
            sql += " and est_usuario_fk = " + idEstudante;
        }
        Long idInstituicao = (Long) criteria.get(ConversaCriteria.ID_INSTITUICAO);
        if (idInstituicao != null && idInstituicao > 0) {
            sql += " and ido_instituicao_longa_permanencia_fk = " + idInstituicao;
        }

        String nomeIdoso = (String) criteria.get(ConversaCriteria.NOME_IDOSO);
        if (nomeIdoso != null && !nomeIdoso.isEmpty()) {
            sql += " and ido_nome ILIKE '%" + nomeIdoso + "%'";
        }

        String nomeEstudante = (String) criteria.get(ConversaCriteria.NOME_ESTUDANTE);
        if (nomeEstudante != null && !nomeEstudante.isEmpty()) {
            sql += " and est_nome ILIKE '%" + nomeEstudante + "%'";
        }

        return sql;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception {
        String sql = "SELECT COUNT(*) count FROM conversa \n"
                + "                LEFT JOIN idoso ON conve_idoso_fk=ido_usuario_fk \n"
                + "                LEFT JOIN usuario i ON i.usu_id=ido_usuario_fk \n"
                + "                LEFT JOIN estudante ON conve_estudante_fk=est_usuario_fk \n"
                + "                LEFT JOIN usuario e ON e.usu_id=est_usuario_fk WHERE 1=1 ";
        sql += this.applyCriteria(criteria);

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);

        Long count = null;
        if (rs.next()) {
            count = rs.getLong("count");
        }

        rs.close();
        s.close();

        return count;
    }

    @Override
    public void update(Connection conn, Conversa entity) throws Exception {
        String sql = "UPDATE conversa SET conve_estudante_fk=?, conve_idoso_fk=?, conve_estudante_avaliacao=?,"
                + " conve_idoso_avaliacao=?, conve_data_hora_inicio=?, conve_data_hora_fim=?, conve_duracao=?,"
                + " conve_gravacao_fk=? WHERE conve_id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, entity.getEstudante().getId());
        if (entity.getIdoso() != null) {
            ps.setLong(++i, entity.getIdoso().getId());
        } else {
            ps.setNull(++i, Types.BIGINT);
        }
        if (entity.getEstudanteAvaliacao() != null) {
            ps.setInt(++i, entity.getEstudanteAvaliacao());
        } else {
            ps.setNull(++i, Types.INTEGER);
        }
        if (entity.getIdosoAvaliacao() != null) {
            ps.setInt(++i, entity.getIdosoAvaliacao());
        } else {
            ps.setNull(++i, Types.INTEGER);
        }
        if (entity.getDataHoraInicio() != null) {
            ps.setTimestamp(++i, entity.getDataHoraInicio());
        } else {
            ps.setNull(++i, Types.TIMESTAMP);
        }
        if (entity.getDatahoraFim() != null) {
            ps.setTimestamp(++i, entity.getDatahoraFim());
        } else {
            ps.setNull(++i, Types.TIMESTAMP);
        }
        if (entity.getDuracao() != null) {
            ps.setFloat(++i, entity.getDuracao());
        } else {
            ps.setNull(++i, Types.FLOAT);
        }
        if (entity.getGravacao().getAudio() != null && entity.getGravacao().getAudio().longValue() != 0) {
            ps.setLong(++i, entity.getGravacao().getId());
        } else {
            ps.setNull(++i, Types.BIGINT);
        }
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String query = "DELETE FROM conversa WHERE conve_id=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

}
