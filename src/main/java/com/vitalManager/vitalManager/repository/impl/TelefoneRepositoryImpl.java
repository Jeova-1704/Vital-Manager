package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TelefoneRepositoryImpl implements TelefoneRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<TelefoneModel> rowMapper = new RowMapper<TelefoneModel>() {
        @Override
        public TelefoneModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            TelefoneModel telefone = new TelefoneModel();

            telefone.setIdTelefone(rs.getInt("id_telefone"));
            telefone.setIdUsuarioFK(rs.getInt("id_usuario_fk"));
            telefone.setContato(rs.getString("contato"));
            telefone.setTipo(rs.getString("tipo"));

            return telefone;
        }
    };

    @Override
    public List<TelefoneModel> findAll() {
        String sql = "SELECT * FROM telefone_usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public Optional<TelefoneModel> findByPhoneId(int id) {
        String sql = "SELECT * FROM telefone_usuario WHERE id_telefone = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Integer> findByUserId(int id) {
        String sql = "SELECT id_telefone FROM telefone_usuario WHERE id_usuario_fk = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{id}, Integer.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int save(TelefoneModel telefoneModel) {
        String sql = "INSERT INTO telefone_usuario (id_usuario_fk,contato,tipo) " +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, telefoneModel.getIdUsuarioFK(),telefoneModel.getContato(),telefoneModel.getTipo());
    }

    @Override
    public int update(TelefoneModel telefoneModel) {
        String sql = "UPDATE telefone_usuario SET contato = ? , tipo = ?  WHERE id_telefone = ?";
        return jdbcTemplate.update(sql, telefoneModel.getContato(),telefoneModel.getTipo(),telefoneModel.getIdTelefone());
    }

    @Override
    public int deleteByUserId(int id) {
        String sql = "DELETE FROM telefone_usuario WHERE id_usuario_fk = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsByUserId(int id) {
        String sql = "SELECT COUNT(*) FROM telefone_usuario WHERE id_usuario_fk = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }




}