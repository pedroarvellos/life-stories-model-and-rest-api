package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.criteria.IdosoCriteria;
import br.com.lifestories.model.criteria.UsuarioCriteria;
import br.com.lifestories.model.entity.Administrador;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcelo
 */
public class UsuarioDAO implements BaseDAO<Usuario> {

    @Override
    public void create(Connection conn, Usuario entity) throws Exception { //Marcelo
        String sql = "INSERT INTO usuario(usu_nome, usu_senha, usu_tipo) VALUES (?, ?, ?)RETURNING usu_id;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getSenha());
        ps.setString(++i, entity.getTipo());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entity.setId(rs.getLong("usu_id"));
        }
        rs.close();
        ps.close();

        if (entity instanceof Estudante) {
            EstudanteDAO dao = new EstudanteDAO();
            dao.create(conn, (Estudante) entity);

        } else if (entity instanceof InstituicaoLongaPermanencia) {
            InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
            dao.create(conn, (InstituicaoLongaPermanencia) entity);

        } else if (entity instanceof Idoso) {
            IdosoDAO dao = new IdosoDAO();
            dao.create(conn, (Idoso) entity);

        } else if (entity instanceof Administrador) {
            AdministradorDAO dao = new AdministradorDAO();
            dao.create(conn, (Administrador) entity);
        }

    }

    @Override
    public Usuario readById(Connection conn, Long id) throws Exception { //Pedro
        String sql = "SELECT * FROM usuario WHERE usu_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setLong(++i, id);

        ResultSet rs = ps.executeQuery();

        Usuario entity = null;

        if (rs.next()) {

            if (rs.getString("usu_tipo").equals("ido")) {
                entity = new Idoso();
                entity.setId(id);
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                IdosoDAO dao = new IdosoDAO();
                dao.readById(conn, (Idoso) entity);
            } else if (rs.getString("usu_tipo").equals("est")) {
                entity = new Estudante();
                entity.setId(id);
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                EstudanteDAO dao = new EstudanteDAO();
                dao.readById(conn, (Estudante) entity);
            } else if (rs.getString("usu_tipo").contains("ins")) {
                entity = new InstituicaoLongaPermanencia();
                entity.setId(id);
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
                dao.readById(conn, (InstituicaoLongaPermanencia) entity);
            } else if (rs.getString("usu_tipo").equals("adm")) {
                entity = new Administrador();
                entity.setId(id);
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                AdministradorDAO dao = new AdministradorDAO();
                dao.readById(conn, (Administrador) entity);
            }
        }

        rs.close();
        ps.close();

        return entity;
    }

    @Override
    public List<Usuario> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception { //Pedro
        List<Usuario> usuarioList = new ArrayList<>();
        String sql = "SELECT * FROM usuario \n"
                + "LEFT JOIN instituicao_longa_permanencia instituicao ON instituicao.ins_usuario_fk = usuario.usu_id\n"
                + "LEFT JOIN idoso ON idoso.ido_usuario_fk = usuario.usu_id\n"
                + "LEFT JOIN estudante ON estudante.est_usuario_fk = usuario.usu_id WHERE 1=1 ";
        sql += this.applyCriteria(criteria);
        
        sql += " ORDER BY usu_nome ASC";

        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset >= 0) {
            sql += " offset " + offset;
        }

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            Usuario entity = null;

            if (rs.getString("usu_tipo").equals("ido")) {
                entity = new Idoso();
                entity.setId(rs.getLong("usu_id"));
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                IdosoDAO dao = new IdosoDAO();
                dao.readById(conn, (Idoso) entity);
            } else if (rs.getString("usu_tipo").equals("est")) {
                entity = new Estudante();
                entity.setId(rs.getLong("usu_id"));
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                EstudanteDAO dao = new EstudanteDAO();
                dao.readById(conn, (Estudante) entity);
            } else if (rs.getString("usu_tipo").contains("ins")) {
                entity = new InstituicaoLongaPermanencia();
                entity.setId(rs.getLong("usu_id"));
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
                dao.readById(conn, (InstituicaoLongaPermanencia) entity);
            } else if (rs.getString("usu_tipo").equals("adm")) {
                entity = new Administrador();
                entity.setId(rs.getLong("usu_id"));
                entity.setNome(rs.getString("usu_nome"));
                entity.setSenha(rs.getString("usu_senha"));
                entity.setTipo(rs.getString("usu_tipo"));

                AdministradorDAO dao = new AdministradorDAO();
                dao.readById(conn, (Administrador) entity);
            }

            usuarioList.add(entity);
        }

        rs.close();
        s.close();

        return usuarioList;
    }

    @Override
    public String applyCriteria(Map<Long, Object> criteria) throws Exception { //Pedro
        String sql = " ";

        Boolean administrador = (Boolean) criteria.get(UsuarioCriteria.ADM_TYPE);
        if (administrador != null) {
            if (administrador) {
                sql += " and usu_tipo = 'adm'";
            }
        }

        Boolean idoso = (Boolean) criteria.get(UsuarioCriteria.IDO_TYPE);
        if (idoso != null) {
            if (idoso) {
                sql += " and usu_tipo = 'ido'";
            }
        }

        Boolean instituicao = (Boolean) criteria.get(UsuarioCriteria.INS_TYPE);
        if (instituicao != null) {
            if (instituicao) {
                sql += " and usu_tipo ILIKE '%ins%'";
            }
        }

        Boolean estudante = (Boolean) criteria.get(UsuarioCriteria.EST_TYPE);
        if (estudante != null) {
            if (estudante) {
                sql += " and usu_tipo = 'est'";
            }
        }

        Long idosoInstituicao = (Long) criteria.get(UsuarioCriteria.IDOSO_INSTITUICAO);
        if (idosoInstituicao != null && idosoInstituicao > 0) {
            sql += " and idoso.ido_instituicao_longa_permanencia_fk = " + idosoInstituicao;
        }

        String nomeUsuario = (String) criteria.get(UsuarioCriteria.NOME_USUARIO);
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            sql += " and usu_nome ILIKE '%" + nomeUsuario + "%'";
        }

        String statusInstituicao = (String) criteria.get(UsuarioCriteria.INSTITUICAO_STATUS);
        if (statusInstituicao != null && !statusInstituicao.isEmpty()) {
            sql += " and usu_tipo = '" + statusInstituicao + "'";
        }

        String emailInstituicao = (String) criteria.get(UsuarioCriteria.INSTITUICAO_EMAIL);
        if (emailInstituicao != null && !emailInstituicao.isEmpty()) {
            sql += " and ins_email = '" + emailInstituicao + "'";
        }

        String emailEstudante = (String) criteria.get(UsuarioCriteria.ESTUDANTE_EMAIL);
        if (emailEstudante != null && !emailEstudante.isEmpty()) {
            sql += " and est_email = '" + emailEstudante + "'";
        }

        return sql;
    }

    @Override
    public Long countByCriteria(Connection conn, Map<Long, Object> criteria) throws Exception { //Pedro
        String sql = "SELECT COUNT(*) count FROM usuario \n"
                + "LEFT JOIN idoso ON usu_id = ido_usuario_fk \n"
                + "LEFT JOIN instituicao_longa_permanencia ON usu_id = ins_usuario_fk \n"
                + "LEFT JOIN estudante ON usu_id = est_usuario_fk \n"
                + "LEFT JOIN administrador ON usu_id = adm_usuario_fk \n"
                + "WHERE 1=1";
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
    public void update(Connection conn, Usuario entity) throws Exception {//Marcelo
        String sql = "UPDATE usuario SET usu_nome=?, usu_senha=?, usu_tipo=? WHERE usu_id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getNome());
        ps.setString(++i, entity.getSenha());
        ps.setString(++i, entity.getTipo());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();

        if (entity instanceof Estudante) {
            EstudanteDAO dao = new EstudanteDAO();
            dao.update(conn, (Estudante) entity);

        } else if (entity instanceof InstituicaoLongaPermanencia) {
            InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
            dao.update(conn, (InstituicaoLongaPermanencia) entity);

        } else if (entity instanceof Idoso) {
            IdosoDAO dao = new IdosoDAO();
            dao.update(conn, (Idoso) entity);

        } else if (entity instanceof Administrador) {
            AdministradorDAO dao = new AdministradorDAO();
            dao.update(conn, (Administrador) entity);
        }
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {//Marcelo
        String sql = "UPDATE usuario SET usu_tipo=? WHERE usu_id=?;";
        PreparedStatement ps = conn.prepareStatement(sql);

        Usuario entity = readById(conn, id);

        int i = 0;
        entity.setTipo("d" + entity.getTipo());
        ps.setString(++i, entity.getTipo());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

}
