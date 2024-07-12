package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.MedicoModel;
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
public class MedicoRepositoryImpl implements MedicoRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<MedicoModel> rowMapper = new RowMapper<MedicoModel>() {
        @Override
        public MedicoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
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

            MedicoModel medico = new MedicoModel();
            medico.setIdMedico(rs.getInt("id_medico"));
            medico.setUsuario(usuario);
            medico.setSalario(rs.getBigDecimal("salario"));
            medico.setEspecialidade(rs.getString("especialidade"));
            medico.setCrm(rs.getString("crm"));
            medico.setDataContratacao(rs.getTimestamp("data_contratacao").toLocalDateTime());

            return medico;
        }
    };

    @Override
    public List<MedicoModel> findAll() {
        String sql = "SELECT u.*, m.* FROM usuario u JOIN medico m ON u.id_usuario = m.id_usuario_fk";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<MedicoModel> findById(int id) {
        String sql = "SELECT u.*, m.* FROM usuario u JOIN medico m ON u.id_usuario = m.id_usuario_fk WHERE m.id_medico = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(MedicoModel medico) {
        String sql = "INSERT INTO medico (id_usuario_fk, salario, especialidade, crm, data_contratacao) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                medico.getUsuario().getIdUsuario(),
                medico.getSalario(),
                medico.getEspecialidade(),
                medico.getCrm(),
                medico.getDataContratacao());
    }

    @Override
    public int update(MedicoModel medico) {
        String sql = "UPDATE Medico SET salario = ?, especialidade = ?, crm = ?, data_contratacao = ? WHERE id_medico = ?";
        return jdbcTemplate.update(sql,
                medico.getSalario(),
                medico.getEspecialidade(),
                medico.getCrm(),
                medico.getDataContratacao(),
                medico.getIdMedico());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Medico WHERE id_medico = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Medico WHERE id_medico = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Optional<MedicoModel> findByUsuarioEmail(String email) {
        String sql = "SELECT u.*, m.* FROM usuario u JOIN medico m ON u.id_usuario = m.id_usuario_fk WHERE u.email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
