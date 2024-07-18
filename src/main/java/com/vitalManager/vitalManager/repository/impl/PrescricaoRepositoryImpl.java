package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.PrescricaoModel;
import com.vitalManager.vitalManager.repository.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PrescricaoRepositoryImpl implements PrescricaoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PrescricaoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PrescricaoModel> prescricaoRowMapper = new RowMapper<PrescricaoModel>() {
        @Override
        public PrescricaoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrescricaoModel prescricao = new PrescricaoModel();
            prescricao.setIdPrescricao(rs.getInt("id_prescricao"));
            prescricao.setIdConsultaFk(rs.getInt("id_consulta_fk"));
            prescricao.setInstrucoes(rs.getString("instrucoes"));
            return prescricao;
        }
    };

    @Override
    public List<PrescricaoModel> findAll() {
        String sql = "SELECT * FROM prescricao";
        return jdbcTemplate.query(sql, prescricaoRowMapper);
    }

    @Override
    public Optional<PrescricaoModel> findById(int id) {
        String sql = "SELECT * FROM prescricao WHERE id_prescricao = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, prescricaoRowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(PrescricaoModel prescricao) {
        String sql = "INSERT INTO prescricao (id_consulta_fk, instrucoes) VALUES (?, ?)";
        return jdbcTemplate.update(sql, prescricao.getIdConsultaFk(), prescricao.getInstrucoes());
    }

    @Override
    public int update(PrescricaoModel prescricao) {
        String sql = "UPDATE prescricao SET id_consulta_Fk = ?, instrucoes = ? WHERE id_prescricao = ?";
        return jdbcTemplate.update(sql, prescricao.getConsulta().getIdConsulta(), prescricao.getInstrucoes(), prescricao.getIdPrescricao());
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM prescricao WHERE id_prescricao = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM prescricao WHERE id_prescricao = ?";
        return jdbcTemplate.update(sql, id);
    }

}
