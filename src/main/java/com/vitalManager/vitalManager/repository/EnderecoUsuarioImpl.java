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
            userAdress.setIdEnderecoUsuario(rs.getInt("ID_Endereco_Usuario"));
            userAdress.setIdUsuarioFK(rs.getInt("ID_Usuario_FK"));
            userAdress.setCep(rs.getString("CEP"));
            userAdress.setRua(rs.getString("Rua"));
            userAdress.setBairro(rs.getString("Bairro"));
            userAdress.setCidade(rs.getString("Cidade"));
            userAdress.setEstado(rs.getString("Estado"));
            userAdress.setPais(rs.getString("Pais"));
            userAdress.setNumeroCasa(rs.getString("Numero_casa"));
            return userAdress;
        }
    };

    @Override
    public List<EnderecoUsuarioModel> findAll() {
        String sql = "SELECT * FROM endereco_usuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<EnderecoUsuarioModel> findByUserId(int id) {
        String sql = "SELECT * FROM endereco_usuario WHERE ID_Usuario_FK = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "INSERT INTO endereco_usuario (CEP, Rua, Bairro, Cidade, Estado, Pais,Numero_casa,ID_Usuario_FK) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa(),enderecoUsuarioModel.getIdUsuarioFK());
    }

    @Override
    public int update(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "UPDATE endereco_usuario SET CEP = ?, Rua = ?, Bairro = ?, Cidade = ?, Estado = ?, Pais = ?, Numero_casa = ? WHERE ID_Endereco_Usuario = ?";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa(),enderecoUsuarioModel.getIdEnderecoUsuario());
    }

    @Override
    public int deleteByUserId(int id) {
        String sql = "DELETE FROM endereco_usuario WHERE ID_Usuario_FK = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsByUserId(int id) {
        String sql = "SELECT COUNT(*) FROM endereco_usuario WHERE ID_Usuario_FK = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}