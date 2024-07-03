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
            userAdress.setIdUsuarioFK(rs.getInt("id_usuario_fk"));
            userAdress.setCep(rs.getString("cep"));
            userAdress.setRua(rs.getString("rua"));
            userAdress.setBairro(rs.getString("bairro"));
            userAdress.setCidade(rs.getString("cidade"));
            userAdress.setEstado(rs.getString("estado"));
            userAdress.setPais(rs.getString("pais"));
            userAdress.setNumeroCasa(rs.getString("numero_casa"));
            return userAdress;
        }
    };

    @Override
    public List<EnderecoUsuarioModel> findAll() {
        String sql = "SELECT * FROM enderecousuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<EnderecoUsuarioModel> findByUserId(int id) {
        String sql = "SELECT * FROM enderecousuario WHERE id_usuario_fk = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "INSERT INTO enderecousuario (cep, rua, bairro, cidade, estado, pais,numero_casa,id_usuario_fk) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa(),enderecoUsuarioModel.getIdUsuarioFK());
    }

    @Override
    public int update(EnderecoUsuarioModel enderecoUsuarioModel) {
        String sql = "UPDATE enderecousuario SET cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, numeroCasa = ? WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, enderecoUsuarioModel.getCep(), enderecoUsuarioModel.getRua(), enderecoUsuarioModel.getBairro(),
                enderecoUsuarioModel.getCidade(), enderecoUsuarioModel.getEstado(), enderecoUsuarioModel.getPais(),
                enderecoUsuarioModel.getNumeroCasa());
    }

    @Override
    public int deleteByUserId(int id) {
        String sql = "DELETE FROM enderecousuario WHERE id_usuario_fk = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsByUserId(int id) {
        String sql = "SELECT COUNT(*) FROM enderecousuario WHERE id_usuario_fk = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }
}
