package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.repository.TelefoneFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TelefoneFornecedorRepositoryImpl implements TelefoneFornecedorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<TelefoneFornecedorModel> rowMapper = new RowMapper<TelefoneFornecedorModel>() {
        @Override
        public TelefoneFornecedorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            TelefoneFornecedorModel telefone = new TelefoneFornecedorModel();

            telefone.setTelefone(rs.getString("telefone"));
            telefone.setIdFornecedor(rs.getInt("id_fornecedor_fk"));
            telefone.setIdTelefoneFornecedor(rs.getInt("id_telefone_fornecedor"));

            return telefone;
        }
    };

    @Override
    public List<TelefoneFornecedorModel> findAll() {
        String sql = "SELECT * FROM telefone_fornecedor";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<TelefoneFornecedorModel> findBySupplierId(int id) {
        String sql = "SELECT * FROM telefone_fornecedor WHERE id_fornecedor_fk = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(TelefoneFornecedorModel telefoneFornecedorModel) {
        String sql = "INSERT INTO telefone_fornecedor (id_fornecedor_fk,telefone) " +
                "VALUES (?, ?)";
        return jdbcTemplate.update(sql,telefoneFornecedorModel.getIdFornecedor(), telefoneFornecedorModel.getTelefone());
    }

    @Override
    public int update(TelefoneFornecedorModel telefoneFornecedorModel) {
        String sql = "UPDATE telefone_fornecedor SET telefone = ?  WHERE id_fornecedor_fk = ?";
        return jdbcTemplate.update(sql, telefoneFornecedorModel.getTelefone() ,telefoneFornecedorModel.getIdFornecedor());
    }

    @Override
    public int deleteBySupplierId(int id) {
        String sql = "DELETE FROM telefone_fornecedor WHERE id_fornecedor_fk = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsBySupplierId(int id) {
        String sql = "SELECT COUNT(*) FROM telefone_fornecedor WHERE id_fornecedor_fk = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }




}