package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.MedicoModel;
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
            MedicoModel medico = new MedicoModel();
            medico.setIdUsuario(rs.getInt("id_usuario_fk"));
            medico.setNome(rs.getString("nome"));
            medico.setSobrenome(rs.getString("sobrenome"));
            medico.setEmail(rs.getString("email"));
            medico.setSenha(rs.getString("senha"));
            medico.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            medico.setSexo(rs.getString("sexo"));
            medico.setDate(rs.getTimestamp("data_criacao").toLocalDateTime());
            medico.setSalario(rs.getBigDecimal("salario"));
            medico.setEspecialidade(rs.getString("especialidade"));
            medico.setCRM(rs.getString("crm"));
            medico.setData_contratacao(rs.getTimestamp("data_contratacao").toLocalDateTime());
            return medico;
        }
    };

    @Override
    public List<MedicoModel> findAll() {
        String sql = "SELECT u.*, m.* FROM usuario u JOIN medico m ON u.id_usuario = m.id_usuario_fk";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MedicoModel medico = new MedicoModel();
            medico.setIdUsuario(rs.getInt("id_usuario"));
            medico.setNome(rs.getString("nome"));
            medico.setSobrenome(rs.getString("sobrenome"));
            medico.setEmail(rs.getString("email"));
            medico.setSenha(rs.getString("senha"));
            medico.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
            medico.setSexo(rs.getString("sexo"));
            medico.setDate(rs.getTimestamp("data_criacao").toLocalDateTime());
            medico.setSalario(rs.getBigDecimal("salario"));
            medico.setEspecialidade(rs.getString("especialidade"));
            medico.setCRM(rs.getString("crm"));
            medico.setData_contratacao(rs.getTimestamp("data_contratacao").toLocalDateTime());
            return medico;
        });
    }

    @Override
    public Optional<MedicoModel> findById(int id) {
        String sql = "SELECT * FROM Medico WHERE id_usuario_fk = ?";
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
                medico.getIdUsuario(),
                medico.getSalario(),
                medico.getEspecialidade(),
                medico.getCRM(),
                medico.getData_contratacao());
    }

    @Override
    public int update(MedicoModel medico) {
        String sql = "UPDATE Medico SET nome = ?, sobrenome = ?, email = ?, senha = ?, data_nascimento = ?, sexo = ?, data_criacao = ?, salario = ?, especialidade = ?, crm = ?, data_contratacao = ? WHERE id_usuario_fk = ?";
        return jdbcTemplate.update(sql,
                medico.getNome(),
                medico.getSobrenome(),
                medico.getEmail(),
                medico.getSenha(),
                medico.getDataNascimento(),
                medico.getSexo(),
                medico.getDate(),
                medico.getSalario(),
                medico.getEspecialidade(),
                medico.getCRM(),
                medico.getData_contratacao(),
                medico.getIdUsuario());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Medico WHERE id_usuario_fk = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Medico WHERE id_usuario_fk = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
