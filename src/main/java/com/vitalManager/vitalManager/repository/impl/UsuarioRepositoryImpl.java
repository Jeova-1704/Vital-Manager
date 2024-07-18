package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.EnderecoUsuarioRepository;
import com.vitalManager.vitalManager.repository.TelefoneRepository;
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
    private TelefoneRepository telefoneRepository;

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

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

            List<TelefoneModel> telefones = telefoneRepository.getTelefonesByUserId(user.getIdUsuario());
            user.setTelefoneUsuario(telefones);

            EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioRepository.findByUserId(user.getIdUsuario());
            user.setEnderecoUsuario(enderecoUsuarioModel);

            return user;
        }
    };

    @Override
    public List<UsuarioModel> findAll() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public Optional<UsuarioModel> findById(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
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
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    @Transactional
    public void deleteUsuarioAndRelatedData(int id) {
        // Excluir dados relacionados em outras tabelas antes do usuário principal
        deleteConsultaByUsuarioId(id);
        deleteEnderecoUsuario(id);
        deleteTelefoneUsuario(id);
        deleteProntuarioByUsuarioId(id);
        deleteExameByPacienteId(id);
        deletePacienteByUsuarioId(id);
        deletePrescricaoByConsultaId(id);

        // Excluir o próprio usuário
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

    private void deleteProntuarioByUsuarioId(int idUsuario) {
        String sqlCheck = "SELECT COUNT(*) FROM Prontuario WHERE ID_Paciente_FK IN (SELECT ID_Paciente FROM Paciente WHERE ID_Usuario_FK = ?)";
        Integer count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{idUsuario}, Integer.class);
        if (count != null && count > 0) {
            String sqlDelete = "DELETE FROM Prontuario WHERE ID_Paciente_FK IN (SELECT ID_Paciente FROM Paciente WHERE ID_Usuario_FK = ?)";
            jdbcTemplate.update(sqlDelete, idUsuario);
        }
    }

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
            String sqlDelete = "DELETE FROM Exame WHERE ID_Paciente_FK = ?";
            jdbcTemplate.update(sqlDelete, idPaciente);
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
}
