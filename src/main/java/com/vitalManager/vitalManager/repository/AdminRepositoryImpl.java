package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.AdminModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<AdminModel> rowMapper = new RowMapper<AdminModel>() {
        @Override
        public AdminModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo"));
            usuario.setTipo(rs.getString("tipo"));
            usuario.setDate(rs.getTimestamp("data_criacao").toLocalDateTime());

            AdminModel admin = new AdminModel();
            admin.setIdAdmin(rs.getInt("id_admin"));
            admin.setUsuario(usuario);
            admin.setPermissao(rs.getString("permissao"));

            return admin;
        }
    };

    @Override
    public List<AdminModel> findAll() {
        String sql = "SELECT u.*, a.* FROM usuario u JOIN admin a ON u.id_usuario = a.id_usuario_fk";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<AdminModel> findById(int id) {
        String sql = "SELECT u.*, a.* FROM usuario u JOIN admin a ON u.id_usuario = a.id_usuario_fk WHERE a.id_admin = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(AdminModel admin) {
        String sql = "INSERT INTO admin (id_usuario_fk, permissao) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                admin.getUsuario().getIdUsuario(),
                admin.getPermissao());
    }

    @Override
    public int update(AdminModel admin) {
        String sql = "UPDATE Admin SET permissao = ? WHERE id_admin = ?";
        return jdbcTemplate.update(sql,
                admin.getPermissao(),
                admin.getIdAdmin());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Admin WHERE id_admin = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Admin WHERE id_admin = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
