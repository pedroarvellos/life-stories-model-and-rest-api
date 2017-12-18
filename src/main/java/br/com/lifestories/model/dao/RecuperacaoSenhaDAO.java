package br.com.lifestories.model.dao;

import br.com.lifestories.model.base.BaseDAO;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.RecuperacaoSenha;
import br.com.lifestories.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joao Pedro
 */
public class RecuperacaoSenhaDAO implements BaseDAO<RecuperacaoSenha> {

    @Override
    public void create(Connection conn, RecuperacaoSenha entity) throws Exception {
        String sql = "INSERT INTO recuperacao_senha(rec_hash, rec_usuario_fk, rec_ativo) VALUES (?, ?, ?) RETURNING rec_id";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getHash());
        ps.setLong(++i, entity.getUsuario().getId());
        ps.setBoolean(++i, entity.getAtivo());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("rec_id"));
        }

        rs.close();
        ps.close();
    }

    @Override
    public RecuperacaoSenha readById(Connection conn, Long id) throws Exception {
        String sql = "select * from recuperacao_senha \n"
                + "left join usuario on usu_id = rec_usuario_fk \n"
                + "left join estudante on est_usuario_fk = usu_id \n"
                + "left join instituicao_longa_permanencia on ins_usuario_fk = usu_id\n"
                + "where rec_id =? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        RecuperacaoSenha recuperacaoSenha = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            recuperacaoSenha = new RecuperacaoSenha();
            recuperacaoSenha.setId(rs.getLong("rec_id"));
            recuperacaoSenha.setAtivo(rs.getBoolean("rec_ativo"));
            recuperacaoSenha.setHash(rs.getString("rec_hash"));

            Usuario usuario = null;
            if (usuario == null) {
                if (rs.getLong("est_usuario_fk") > 0) {
                    usuario = new Estudante();
                    usuario.setId(id);
                    usuario.setNome(rs.getString("usu_nome"));
                    usuario.setTipo(rs.getString("usu_tipo"));
                    EstudanteDAO dao = new EstudanteDAO();
                    dao.readById(conn, (Estudante) usuario);
                } else if ((rs.getLong("ins_usuario_fk") > 0)) {
                    usuario = new InstituicaoLongaPermanencia();
                    usuario.setId(id);
                    usuario.setNome(rs.getString("usu_nome"));
                    usuario.setTipo(rs.getString("usu_tipo"));
                    InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
                    dao.readById(conn, (InstituicaoLongaPermanencia) usuario);
                }
            }            
            recuperacaoSenha.setUsuario(usuario);
        }

        rs.close();
        ps.close();

        return recuperacaoSenha;
    }

    @Override
    public List<RecuperacaoSenha> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
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
    public void update(Connection conn, RecuperacaoSenha entity) throws Exception {
        String sql = "UPDATE recuperacao_senha SET rec_hash=?, rec_ativo=? WHERE rec_id=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        int i = 0;
        ps.setString(++i, entity.getHash());
        ps.setBoolean(++i, entity.getAtivo());
        ps.setLong(++i, entity.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM recuperacao_senha WHERE rec_id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
    }

    public RecuperacaoSenha readByHashCode(Connection conn, String codigo) throws Exception {
        String sql = "select * from recuperacao_senha \n"
                + "left join usuario on usu_id = rec_usuario_fk \n"
                + "left join estudante on est_usuario_fk = usu_id \n"
                + "left join instituicao_longa_permanencia on ins_usuario_fk = usu_id\n"
                + "where rec_hash =? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigo);
        RecuperacaoSenha recuperacaoSenha = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            recuperacaoSenha = new RecuperacaoSenha();
            recuperacaoSenha.setId(rs.getLong("rec_id"));
            recuperacaoSenha.setAtivo(rs.getBoolean("rec_ativo"));
            recuperacaoSenha.setHash(rs.getString("rec_hash"));

            Usuario usuario = null;
            if (usuario == null) {
                if (rs.getLong("est_usuario_fk") > 0) {
                    usuario = new Estudante();
                    usuario.setId(rs.getLong("usu_id"));
                    usuario.setNome(rs.getString("usu_nome"));
                    usuario.setTipo(rs.getString("usu_tipo"));
                    EstudanteDAO dao = new EstudanteDAO();
                    dao.readById(conn, (Estudante) usuario);
                } else if ((rs.getLong("ins_usuario_fk") > 0)) {
                    usuario = new InstituicaoLongaPermanencia();
                    usuario.setId(rs.getLong("usu_id"));
                    usuario.setNome(rs.getString("usu_nome"));
                    usuario.setTipo(rs.getString("usu_tipo"));
                    InstituicaoLongaPermanenciaDAO dao = new InstituicaoLongaPermanenciaDAO();
                    dao.readById(conn, (InstituicaoLongaPermanencia) usuario);
                }
            }            
            recuperacaoSenha.setUsuario(usuario);
        }

        rs.close();
        ps.close();

        return recuperacaoSenha;
    }

}
