package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

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
            user.setCpf(rs.getString("CPF"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            user.setSexo(rs.getString("sexo"));
            user.setTipo(rs.getString("tipo"));
            user.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
            return user;
        }
    };

    private RowMapper<EnderecoUsuarioModel> enderecoRowMapper = new RowMapper<EnderecoUsuarioModel>() {
        @Override
        public EnderecoUsuarioModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            EnderecoUsuarioModel endereco = new EnderecoUsuarioModel();
            endereco.setIdEnderecoUsuario(rs.getInt("id_endereco_usuario"));
            endereco.setIdUsuarioFK(rs.getInt("id_usuario_fk"));
            endereco.setRua(rs.getString("rua"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setPais(rs.getString("pais"));
            endereco.setCep(rs.getString("cep"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumeroCasa(rs.getString("numero_casa"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setEstado(rs.getString("estado"));
            return endereco;
        }
    };

    private RowMapper<TelefoneModel> telefoneRowMapper = (rs, rowNum) -> {
        TelefoneModel telefone = new TelefoneModel();
        telefone.setIdTelefone(rs.getInt("id_telefone"));
        telefone.setIdUsuarioFK(rs.getInt("id_usuario_fk"));
        telefone.setContato(rs.getString("contato"));
        telefone.setTipo(rs.getString("tipo"));
        return telefone;
    };

    @Override
    public List<UsuarioModel> findAll() {
        String usuarioSql = "SELECT * FROM usuario";
        String enderecoSql = "SELECT * FROM endereco_usuario WHERE id_usuario_fk = ?";
        String telefoneSql = "SELECT * FROM telefone_usuario WHERE id_usuario_fk = ?";

        try {
            List<UsuarioModel> usuarios = jdbcTemplate.query(usuarioSql, rowMapper);

            for (UsuarioModel usuario : usuarios) {
                try {
                    EnderecoUsuarioModel endereco = jdbcTemplate.queryForObject(enderecoSql, new Object[]{usuario.getIdUsuario()}, enderecoRowMapper);
                    usuario.setEnderecoUsuario(endereco);
                } catch (EmptyResultDataAccessException e) {
                    usuario.setEnderecoUsuario(null);
                }

                List<TelefoneModel> telefones = jdbcTemplate.query(telefoneSql, new Object[]{usuario.getIdUsuario()}, telefoneRowMapper);
                usuario.setTelefoneUsuario(telefones);
            }

            return usuarios;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    public Optional<UsuarioModel> findById(int id) {
        String usuarioSql = "SELECT * FROM usuario WHERE id_usuario = ?";
        String enderecoSql = "SELECT * FROM endereco_usuario WHERE id_usuario_fk = ?";
        String telefoneSql = "SELECT * FROM telefone_usuario WHERE id_usuario_fk = ?";

        try {
            UsuarioModel usuario = jdbcTemplate.queryForObject(usuarioSql, new Object[]{id}, rowMapper);

            try {
                EnderecoUsuarioModel endereco = jdbcTemplate.queryForObject(enderecoSql, new Object[]{id}, enderecoRowMapper);
                usuario.setEnderecoUsuario(endereco);
            } catch (EmptyResultDataAccessException e) {
                usuario.setEnderecoUsuario(null);
            }

            List<TelefoneModel> telefones = jdbcTemplate.query(telefoneSql, new Object[]{id}, telefoneRowMapper);
            usuario.setTelefoneUsuario(telefones);

            return Optional.of(usuario);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(UsuarioModel usuario) {
        String sql = "INSERT INTO Usuario (nome, sobrenome, CPF, email, senha, data_nascimento, sexo, tipo, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo(), usuario.getTipo(),
                usuario.getDataCriacao());
    }

    @Override
    public int update(UsuarioModel usuario) {
        String sql = "UPDATE Usuario SET nome = ?, sobrenome = ?, CPF = ?, email = ?, senha = ?, data_nascimento = ?, sexo = ?, tipo = ?, data_criacao = ? WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo(), usuario.getTipo(),
                usuario.getDataCriacao(), usuario.getIdUsuario());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Optional<UsuarioModel> findByEmail(String email) {
        String usuarioSql = "SELECT * FROM usuario WHERE email = ?";
        String enderecoSql = "SELECT * FROM endereco_usuario WHERE id_usuario_fk = ?";
        String telefoneSql = "SELECT * FROM telefone_usuario WHERE id_usuario_fk = ?";

        try {
            UsuarioModel usuario = jdbcTemplate.queryForObject(usuarioSql, new Object[]{email}, rowMapper);

            try {
                EnderecoUsuarioModel endereco = jdbcTemplate.queryForObject(enderecoSql, new Object[]{usuario.getIdUsuario()}, enderecoRowMapper);
                usuario.setEnderecoUsuario(endereco);
            } catch (EmptyResultDataAccessException e) {
                usuario.setEnderecoUsuario(null);
            }

            List<TelefoneModel> telefones = jdbcTemplate.query(telefoneSql, new Object[]{usuario.getIdUsuario()}, telefoneRowMapper);
            usuario.setTelefoneUsuario(telefones);

            return Optional.of(usuario);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
