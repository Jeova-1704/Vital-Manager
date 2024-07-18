package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public void deleteUsuarioAndRelatedData(int id) {

        // Excluir exames
        deleteExameByPacienteId(id);

        // Passo 1: Excluir prontuários, removendo referências primeiro
        removeProntuarioReferencesByUsuarioId(id);
        deleteProntuarioByUsuarioId(id);

        // Passo 2: Excluir pacientes
        deletePacienteByUsuarioId(id);

        // Passo 3: Excluir outros dados relacionados
        deleteEnderecoUsuario(id);
        deleteTelefoneUsuario(id);
        deleteConsultaByUsuarioId(id);
        deletePrescricaoByConsultaId(id);

        // Passo 4: Excluir o próprio usuário
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        jdbcTemplate.update(sql, id);

    }

    private void deleteEnderecoUsuario(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Endereco_Usuario WHERE ID_Usuario_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Endereco_Usuario WHERE ID_Usuario_FK = ?";
            jdbcTemplate.update(sqlDelete, idUsuario);
        }
    }

    private void deleteTelefoneUsuario(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Telefone_Usuario WHERE ID_Usuario_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Telefone_Usuario WHERE ID_Usuario_FK = ?";
            jdbcTemplate.update(sqlDelete, idUsuario);
        }
    }

    // ok
    private void deleteProntuarioByUsuarioId(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Prontuario WHERE ID_Paciente_FK IN (SELECT ID_Paciente FROM Paciente WHERE ID_Usuario_FK = ?)";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sql = "DELETE FROM Prontuario WHERE ID_Prontuario IN (SELECT ID_numero_Prontuario_FK FROM Paciente WHERE ID_Usuario_FK = ?)";
            jdbcTemplate.update(sql, idUsuario);
        }
    }
   // ok
    private void deletePacienteByUsuarioId(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Paciente WHERE ID_Usuario_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Paciente WHERE ID_Usuario_FK = ?";
            jdbcTemplate.update(sqlDelete, idUsuario);
        }
    }

    private void deleteConsultaByUsuarioId(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Consulta WHERE Medico_Id_FK IN (SELECT ID_Medico FROM Medico WHERE ID_Usuario_FK = ?)";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Consulta WHERE Medico_Id_FK IN (SELECT ID_Medico FROM Medico WHERE ID_Usuario_FK = ?)";
            jdbcTemplate.update(sqlDelete, idUsuario);
        }
    }

    private void deleteExameByPacienteId(int idPaciente) {
        String sqlCheck = "SELECT COUNT(*) FROM Exame WHERE ID_Paciente_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idPaciente}, Integer.class);
        if (count != null && count > 0) {
            String sql = "DELETE FROM Exame WHERE ID_Paciente_FK IN (SELECT ID_Paciente FROM Paciente WHERE ID_Usuario_FK = ?)";
            jdbcTemplate.update(sql, idPaciente);
        }
    }

    private void deletePrescricaoByConsultaId(int idConsulta) {
        String sqlCheck = "SELECT COUNT(*) FROM Prescricao WHERE ID_Consulta_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idConsulta}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Prescricao WHERE ID_Consulta_FK = ?";
            jdbcTemplate.update(sqlDelete, idConsulta);
        }
    }
    private void removeProntuarioReferencesByUsuarioId(int idUsuario) {
        String sql = "UPDATE Paciente SET ID_numero_Prontuario_FK = NULL WHERE ID_Usuario_FK = ?";
        jdbcTemplate.update(sql, idUsuario);
    }


}

