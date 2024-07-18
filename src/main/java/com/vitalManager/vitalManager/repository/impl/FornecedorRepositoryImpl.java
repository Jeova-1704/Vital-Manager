package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.*;
import com.vitalManager.vitalManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FornecedorRepositoryImpl implements FornecedorRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private TelefoneFornecedorRepository telefoneFornecedorRepository;

    @Autowired
    private EnderecoFornecedorRepository enderecoFornecedorRepository;

    @Autowired
    public FornecedorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<FornecedorModel> rowMapper = new RowMapper<FornecedorModel>() {
        @Override
        public FornecedorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            FornecedorModel fornecedor = new FornecedorModel();
            fornecedor.setIdFornecedor(rs.getInt("id_fornecedor"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCnpj(rs.getString("cnpj"));

            List<TelefoneFornecedorModel> telefones = telefoneFornecedorRepository.getTelefonesBySupplierId(fornecedor.getIdFornecedor());
            fornecedor.setTelefonesFornecedor(telefones);

            EnderecoFornecedorModel enderecoFornecedorModel = enderecoFornecedorRepository.findBySupplierId(fornecedor.getIdFornecedor());
            fornecedor.setEnderecoFornecedorModel(enderecoFornecedorModel);
            return fornecedor;
        }
    };


    @Override
    public List<FornecedorModel> findAll() {
        String sql = "SELECT * FROM Fornecedor";
        List<FornecedorModel> fornecedores = jdbcTemplate.query(sql, rowMapper);

        return fornecedores;
    }

    @Override
    public Optional<FornecedorModel> findById(int id) {
        String sql = "SELECT * FROM Fornecedor WHERE id_fornecedor = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(FornecedorModel fornecedor) {
        String sql = "INSERT INTO Fornecedor (nome, cnpj) VALUES (?, ?)";
        return jdbcTemplate.update(sql, fornecedor.getNome(), fornecedor.getCnpj());
    }

    @Override
    public int update(FornecedorModel fornecedor) {
        String sql = "UPDATE Fornecedor SET nome = ?, cnpj = ? WHERE id_fornecedor = ?";
        return jdbcTemplate.update(sql, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getIdFornecedor());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Fornecedor WHERE id_fornecedor = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Fornecedor WHERE id_fornecedor = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
