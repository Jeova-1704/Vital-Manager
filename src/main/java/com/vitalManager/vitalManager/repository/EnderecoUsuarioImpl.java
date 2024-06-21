package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EnderecoUsuarioImpl implements EnderecoUsuarioRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<EnderecoUsuarioModel> rowMapper = new RowMapper<EnderecoUsuarioModel>() {
        @Override
        public EnderecoUsuarioModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            EnderecoUsuarioModel userAdress = new EnderecoUsuarioModel();
            userAdress.setCep(rs.getString("cep"));
            userAdress.setRua(rs.getString("rua"));
            userAdress.setBairro(rs.getString("bairro"));
            userAdress.setCidade(rs.getString("cidade"));
            userAdress.setEstado(rs.getString("estado"));
            userAdress.setPais(rs.getString("pais"));
            userAdress.setNumeroCasa(rs.getString("numeroCasa"));
            return userAdress;
        }
    };

    @Override
    public List<EnderecoUsuarioModel> findAll() {
        String sql = "SELECT * FROM EnderecoUsuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<EnderecoUsuarioModel> findById(int id) {
        String sql = "SELECT * FROM EnderecoUsuario WHERE id_usuario = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "INSERT INTO Usuario (cep, rua, bairro, cidade, estado, pais,numeroCasa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa());
    }

    @Override
    public int update(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "UPDATE Usuario SET cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, numeroCasa = ? WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM EnderecoUsuario WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM EnderecoUsuario WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
