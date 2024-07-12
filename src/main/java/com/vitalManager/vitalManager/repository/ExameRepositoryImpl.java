package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.ExameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ExameRepositoryImpl implements ExameRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExameRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ExameModel> rowMapper = new RowMapper<ExameModel>() {
        @Override
        public ExameModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExameModel exame = new ExameModel();
            exame.setIdExame(rs.getInt("id_exame"));
            exame.setIdPaciente(rs.getInt("id_paciente_fk"));
            exame.setTipoExame(rs.getString("tipo_exame"));
            exame.setResultado(rs.getString("resultado"));
            exame.setDataExame(rs.getDate("data_exame").toLocalDate());
            return exame;
        }
    };

    @Override
    public List<ExameModel> findAll() {
        String sql = "SELECT * FROM exame";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ExameModel> findById(int id) {
        String sql = "SELECT * FROM exame WHERE id_exame = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(ExameModel exame) {
        String sql = "INSERT INTO exame (id_paciente_fk, tipo_exame, resultado, data_exame) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, exame.getIdPaciente(), exame.getTipoExame(), exame.getResultado(), exame.getDataExame());
    }

    @Override
    public int update(ExameModel exame) {
        String sql = "UPDATE exame SET id_paciente_fk = ?, tipo_exame = ?, resultado = ?, data_exame = ? WHERE id_exame = ?";
        return jdbcTemplate.update(sql, exame.getIdPaciente(), exame.getTipoExame(), exame.getResultado(), exame.getDataExame(), exame.getIdExame());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM exame WHERE id_exame = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM exame WHERE id_exame = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
