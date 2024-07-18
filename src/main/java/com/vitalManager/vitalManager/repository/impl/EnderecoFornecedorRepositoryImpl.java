package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import com.vitalManager.vitalManager.repository.EnderecoFornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoFornecedorRepositoryImpl implements EnderecoFornecedorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<EnderecoFornecedorModel> rowMapper = new RowMapper<EnderecoFornecedorModel>() {
        @Override
        public EnderecoFornecedorModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            EnderecoFornecedorModel enderecoFornecedor = new EnderecoFornecedorModel();
            enderecoFornecedor.setIdEnderecoFornecedor(rs.getInt("id_endereco_fornecedor"));
            enderecoFornecedor.setIdFornecedor(rs.getInt("id_fornecedor_fk"));
            enderecoFornecedor.setCep(rs.getString("cep"));
            enderecoFornecedor.setRua(rs.getString("rua"));
            enderecoFornecedor.setBairro(rs.getString("bairro"));
            enderecoFornecedor.setCidade(rs.getString("cidade"));
            enderecoFornecedor.setPais(rs.getString("pais"));
            enderecoFornecedor.setEstado(rs.getString("estado"));
            enderecoFornecedor.setNumero(rs.getString("numero"));
            return enderecoFornecedor;
        }
    };

    @Override
    public List<EnderecoFornecedorModel> findAll() {
        String sql = "SELECT * FROM endereco_fornecedor";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public EnderecoFornecedorModel findBySupplierId(int id) {
        String sql = "SELECT * FROM endereco_fornecedor WHERE id_fornecedor_fk = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int save(EnderecoFornecedorModel enderecoFornecedorModel) {
        String sql = "INSERT INTO endereco_fornecedor (cep, rua, bairro, cidade, estado, pais, numero, id_fornecedor_fk) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enderecoFornecedorModel.getCep(), enderecoFornecedorModel.getRua(),
                enderecoFornecedorModel.getBairro(), enderecoFornecedorModel.getCidade(), enderecoFornecedorModel.getEstado(),
                enderecoFornecedorModel.getPais(), enderecoFornecedorModel.getNumero(), enderecoFornecedorModel.getIdFornecedor());
    }

    @Override
    public int update(EnderecoFornecedorModel enderecoFornecedorModel) {
        String sql = "UPDATE endereco_fornecedor SET cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, numero = ? " +
                "WHERE id_fornecedor_fk = ?";
        return jdbcTemplate.update(sql, enderecoFornecedorModel.getCep(), enderecoFornecedorModel.getRua(),
                enderecoFornecedorModel.getBairro(), enderecoFornecedorModel.getCidade(), enderecoFornecedorModel.getEstado(),
                enderecoFornecedorModel.getPais(), enderecoFornecedorModel.getNumero(), enderecoFornecedorModel.getIdFornecedor());
    }

    @Override
    public int deleteBySupplierId(int id) {
        String sql = "DELETE FROM endereco_fornecedor WHERE id_fornecedor_fk = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsBySupplierId(int id) {
        String sql = "SELECT COUNT(*) FROM endereco_fornecedor WHERE id_fornecedor_fk = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
