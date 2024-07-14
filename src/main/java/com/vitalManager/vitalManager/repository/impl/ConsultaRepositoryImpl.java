package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.ConsultaModel;
import com.vitalManager.vitalManager.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultaRepositoryImpl implements ConsultaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsultaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ConsultaModel> rowMapper = (rs, rowNum) -> {
        ConsultaModel consulta = new ConsultaModel();
        consulta.setIdConsulta(rs.getInt("ID_Consulta"));
        consulta.setIdProntuario(rs.getInt("Prontuario_Id_FK"));
        consulta.setIdMedico(rs.getInt("Medico_Id_FK"));
        consulta.setData(rs.getTimestamp("Data"));
        consulta.setValor(rs.getBigDecimal("Valor"));
        consulta.setHora(rs.getTime("Hora"));
        consulta.setStatus(rs.getString("Status"));
        consulta.setObservacoes(rs.getString("Observacoes"));
        return consulta;
    };

    @Override
    public List<ConsultaModel> findAll() {
        String sql = "SELECT * FROM Consulta";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ConsultaModel> findById(int id) {
        String sql = "SELECT * FROM Consulta WHERE ID_Consulta = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(ConsultaModel consulta) {
        String sql = "INSERT INTO Consulta (Medico_Id_FK, Prontuario_Id_FK, Data, Valor, Hora, Status, Observacoes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, consulta.getIdMedico(), consulta.getIdProntuario(), consulta.getData(),
                consulta.getValor(), consulta.getHora(), consulta.getStatus(), consulta.getObservacoes());
    }

    @Override
    public int update(ConsultaModel consulta) {
        String sql = "UPDATE Consulta SET Medico_Id_FK = ?, Prontuario_Id_FK = ?, Data = ?, Valor = ?, Hora = ?, " +
                "Status = ?, Observacoes = ? WHERE ID_Consulta = ?";
        return jdbcTemplate.update(sql, consulta.getIdMedico(), consulta.getIdProntuario(), consulta.getData(),
                consulta.getValor(), consulta.getHora(), consulta.getStatus(), consulta.getObservacoes(),
                consulta.getIdConsulta());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Consulta WHERE ID_Consulta = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Consulta WHERE ID_Consulta = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
