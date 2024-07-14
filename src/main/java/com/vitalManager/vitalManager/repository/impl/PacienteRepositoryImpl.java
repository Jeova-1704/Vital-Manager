package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.PacienteModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepositoryImpl implements PacienteRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PacienteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PacienteModel> rowMapper = new RowMapper<PacienteModel>() {
        @Override
        public PacienteModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            usuario.setCpf(rs.getString("CPF"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo"));
            usuario.setTipo(rs.getString("tipo"));
            usuario.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());

            PacienteModel paciente = new PacienteModel();
            paciente.setIdPaciente(rs.getInt("id_paciente"));
            paciente.setUsuario(usuario);
            paciente.setIdUsuarioFK(rs.getInt("id_usuario_fk"));
            paciente.setNumeroProntuario(rs.getInt("numero_prontuario"));

            return paciente;
        }
    };

    @Override
    public List<PacienteModel> findAll() {
        String sql = "SELECT u.*, p.* FROM usuario u LEFT JOIN paciente p ON u.id_usuario = p.id_usuario_fk";
        return jdbcTemplate.query(sql, new RowMapper<PacienteModel>() {
            @Override
            public PacienteModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                PacienteModel paciente = new PacienteModel();
                UsuarioModel usuario = new UsuarioModel();

                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setSexo(rs.getString("sexo"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                paciente.setUsuario(usuario);

                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setIdUsuarioFK(rs.getInt("id_usuario"));
                paciente.setNumeroProntuario(rs.getInt("id_numero_prontuario_fk"));

                return paciente;
            }
        });
    }

    @Override
    public Optional<PacienteModel> findById(int id) {
        String sql = "SELECT u.*, p.* FROM usuario u LEFT JOIN paciente p ON u.id_usuario = p.id_usuario_fk WHERE p.id_paciente = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<PacienteModel>() {
                @Override
                public PacienteModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                    PacienteModel paciente = new PacienteModel();
                    UsuarioModel usuario = new UsuarioModel();

                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSobrenome(rs.getString("sobrenome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    usuario.setSexo(rs.getString("sexo"));
                    usuario.setTipo(rs.getString("tipo"));
                    usuario.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                    paciente.setUsuario(usuario);

                    paciente.setIdPaciente(rs.getInt("id_paciente"));
                    paciente.setIdUsuarioFK(rs.getInt("id_usuario"));
                    paciente.setNumeroProntuario(rs.getInt("id_numero_prontuario_fk"));

                    return paciente;
                }
            }));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(PacienteModel paciente) {
        String sql = "INSERT INTO paciente (id_usuario_fk, id_numero_prontuario_fk) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                paciente.getUsuario().getIdUsuario(),
                paciente.getNumeroProntuario());
    }

    @Override
    public int update(PacienteModel paciente) {
        String sql = "UPDATE paciente SET id_numero_prontuario_fk = ? WHERE id_paciente = ?";
        return jdbcTemplate.update(sql, paciente.getNumeroProntuario(), paciente.getIdPaciente());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Paciente WHERE id_paciente = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Paciente WHERE id_paciente = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }

    public Integer findPacienteIdByUsuarioId(int usuarioId) {
        String sql = "SELECT id_paciente FROM paciente WHERE id_usuario_fk = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{usuarioId}, Integer.class);
        } catch (Exception e) {
            return null;
        }
    }
}
