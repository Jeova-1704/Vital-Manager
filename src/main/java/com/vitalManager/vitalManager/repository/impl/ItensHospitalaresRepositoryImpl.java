package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import com.vitalManager.vitalManager.repository.ItensHospitalaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ItensHospitalaresRepositoryImpl implements ItensHospitalaresRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<ItensHospitalaresModel> rowMapper = new RowMapper<ItensHospitalaresModel>() {
        @Override
        public ItensHospitalaresModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            ItensHospitalaresModel item = new ItensHospitalaresModel();
            item.setIdItensHospitalares(rs.getInt("ID_Itens_Hospitalares"));
            item.setNome(rs.getString("Nome"));
            item.setPreco(rs.getBigDecimal("Preco"));
            item.setDescricao(rs.getString("Descricao"));
            item.setDataValidade(rs.getDate("Data_Validade").toLocalDate());
            item.setQuantidade(rs.getInt("Quantidade"));
            item.setIdPrescricao(rs.getInt("ID_Precricao_FK"));
            item.setIdEstoque(rs.getInt("ID_Estoque"));
            item.setIDFornecedorFK(rs.getInt("ID_Fornecedor_FK"));
            return item;
        }
    };

    @Override
    public ItensHospitalaresModel create(ItensHospitalaresModel item) {
        String sql = "INSERT INTO Itens_Hospitalares (Nome, Preco, Descricao, Data_Validade, Quantidade, ID_Precricao_FK, ID_Estoque, ID_Fornecedor_FK) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getNome(), item.getPreco(), item.getDescricao(), item.getDataValidade(), item.getQuantidade(), item.getIdPrescricao(), item.getIdEstoque(), item.getIDFornecedorFK());
        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        item.setIdItensHospitalares(id);
        return item;
    }


    @Override
    public Optional<ItensHospitalaresModel> findById(Integer id) {
        String sql = "SELECT * FROM Itens_Hospitalares WHERE ID_Itens_Hospitalares = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    @Override
    public List<ItensHospitalaresModel> findAll() {
        String sql = "SELECT * FROM Itens_Hospitalares";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public ItensHospitalaresModel update(ItensHospitalaresModel item) {
        String sql = "UPDATE Itens_Hospitalares SET Nome = ?, Preco = ?, Descricao = ?, Data_Validade = ?, Quantidade = ?,  ID_Precricao_FK = ?, ID_Estoque = ? WHERE ID_Itens_Hospitalares = ?";
        jdbcTemplate.update(sql, item.getNome(), item.getPreco(), item.getDescricao(), item.getDataValidade(), item.getQuantidade(), item.getIdPrescricao(), item.getIdEstoque(), item.getIdItensHospitalares());
        return item;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Itens_Hospitalares WHERE ID_Itens_Hospitalares = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ItensHospitalaresModel> getItensHospitalaresByEstoqueId(Integer idEstoque) {
        String sql = "SELECT * FROM Itens_Hospitalares WHERE ID_Estoque = ?";
        return jdbcTemplate.query(sql, new Object[]{idEstoque}, rowMapper);
    }
}
