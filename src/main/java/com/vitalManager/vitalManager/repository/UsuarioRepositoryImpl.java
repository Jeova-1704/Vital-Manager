package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.Enum.SexoEnum;
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
public class UsuarioRepositoryImpl implements UsuarioRepository{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<UsuarioModel> rowMapper = new RowMapper<UsuarioModel>() {
        @Override
        public UsuarioModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioModel user = new UsuarioModel();
            user.setIdUsuario(rs.getInt("id_usuario"));
            user.setNome(rs.getString("nome"));
            user.setSobrenome(rs.getString("sobrenome"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            user.setSexo(SexoEnum.fromDescricao(rs.getString("sexo")));
            user.setDate(rs.getTimestamp("data_criacao").toLocalDateTime());
            return user;
        }
    };

    @Override
    public List<UsuarioModel> findAll() {
        String sql = "SELECT * FROM Usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<UsuarioModel> findById(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(UsuarioModel usuario) {
        String sql = "INSERT INTO Usuario (nome, sobrenome, email, senha, data_nascimento, sexo, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo().getDescricao(),
                usuario.getDate());
    }

    @Override
    public int update(UsuarioModel usuario) {
        String sql = "UPDATE Usuario SET nome = ?, sobrenome = ?, email = ?, senha = ?, data_nascimento = ?, sexo = ?, data_criacao = ? WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo().getDescricao(),
                usuario.getDate(), usuario.getIdUsuario());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
