package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.EstoqueModel;
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
public class EstoqueRepositoryImpl implements EstoqueRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EstoqueRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<EstoqueModel> rowMapper = new RowMapper<EstoqueModel>() {
        @Override
        public EstoqueModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            EstoqueModel estoque = new EstoqueModel();
            estoque.setIdEstoque(rs.getInt("id_estoque"));
            estoque.setNome(rs.getString("nome"));
            estoque.setDataAtualizacao(rs.getDate("data_atualizacao").toLocalDate());
            estoque.setQuantidade(rs.getInt("quantidade"));

            return estoque;
        }
    };

    @Override
    public List<EstoqueModel> findAll() {
        String sql = "SELECT * FROM estoque";
        List<EstoqueModel> estoques = jdbcTemplate.query(sql, rowMapper);
        return estoques;
    }

    @Override
    public Optional<EstoqueModel> findById(int id) {
        String sql = "SELECT * FROM estoque WHERE id_estoque = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(EstoqueModel estoque) {
        String sql = "INSERT INTO estoque (nome, data_atualizacao, quantidade) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, estoque.getNome(), estoque.getDataAtualizacao(), estoque.getQuantidade());
    }

    @Override
    public int update(EstoqueModel estoque) {
        String sql = "UPDATE estoque SET nome = ?, data_atualizacao = ?, quantidade = ? WHERE id_estoque = ?";
        return jdbcTemplate.update(sql, estoque.getNome(), estoque.getDataAtualizacao(), estoque.getQuantidade(), estoque.getIdEstoque());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM estoque WHERE id_estoque = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM estoque WHERE id_estoque = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
