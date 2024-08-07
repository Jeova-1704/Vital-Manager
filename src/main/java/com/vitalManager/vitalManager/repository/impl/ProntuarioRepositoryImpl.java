package com.vitalManager.vitalManager.repository.impl;

import com.vitalManager.vitalManager.model.ConsultaModel;
import com.vitalManager.vitalManager.model.ProntuarioModel;
import com.vitalManager.vitalManager.repository.ConsultaRepository;
import com.vitalManager.vitalManager.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ProntuarioRepositoryImpl implements ProntuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ConsultaRepository consultaRepository;

    private RowMapper<ProntuarioModel> rowMapper = (rs, rowNum) -> {
        ProntuarioModel prontuario = new ProntuarioModel();
        prontuario.setIdProntuario(rs.getInt("ID_Prontuario"));
        prontuario.setIdPacienteFK(rs.getInt("ID_Paciente_FK"));
        prontuario.setIdade(rs.getInt("Idade"));
        prontuario.setTipoSanguineo(rs.getString("tipo_sanguineo"));
        prontuario.setAltura(rs.getFloat("Altura"));
        prontuario.setPeso(rs.getFloat("Peso"));
        prontuario.setTemperatura(rs.getFloat("Temperatura"));
        prontuario.setPressao(rs.getString("presao"));
        prontuario.setDescricao(rs.getString("Descricao"));
        prontuario.setDataCriacao(rs.getTimestamp("Data_criacao").toLocalDateTime());

        List<ConsultaModel> consultas = consultaRepository.getConsultasByProntuarioId(prontuario.getIdProntuario());
        prontuario.setConsultas(consultas);

        return prontuario;
    };

    @Override
    public List<ProntuarioModel> findAll() {
        String sql = "SELECT * FROM Prontuario";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<ProntuarioModel> findById(int id) {
        String sql = "SELECT * FROM Prontuario WHERE ID_Prontuario = ?";
        try {
            ProntuarioModel prontuario = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
            return Optional.ofNullable(prontuario);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int save(ProntuarioModel prontuario) {
        String sql = "INSERT INTO Prontuario (ID_Paciente_FK, Idade, tipo_sanguineo, Altura, Peso, Temperatura, presao, Descricao, Data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, prontuario.getIdPacienteFK(), prontuario.getIdade(), prontuario.getTipoSanguineo(),
                prontuario.getAltura(), prontuario.getPeso(), prontuario.getTemperatura(), prontuario.getPressao(),
                prontuario.getDescricao(), Timestamp.valueOf(prontuario.getDataCriacao()));
    }

    @Override
    public int update(ProntuarioModel prontuario) {
        String sql = "UPDATE Prontuario SET ID_Paciente_FK = ?, Idade = ?, tipo_sanguineo = ?, Altura = ?, Peso = ?, " +
                "Temperatura = ?, presao = ?, Descricao = ?, Data_criacao = ? WHERE ID_Prontuario = ?";
        return jdbcTemplate.update(sql, prontuario.getIdPacienteFK(), prontuario.getIdade(), prontuario.getTipoSanguineo(),
                prontuario.getAltura(), prontuario.getPeso(), prontuario.getTemperatura(), prontuario.getPressao(),
                prontuario.getDescricao(), Timestamp.valueOf(prontuario.getDataCriacao()), prontuario.getIdProntuario());
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM Prontuario WHERE ID_Prontuario = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Prontuario WHERE ID_Prontuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public Integer findProntuarioIdByUsuarioId(int usuarioId) {
        String sql = "SELECT ID_Prontuario FROM Prontuario WHERE ID_Paciente_FK = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{usuarioId}, Integer.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProntuarioModel findProntuarioIdByPacienteId(int pacienteId) {
        String sql = "SELECT * FROM Prontuario WHERE ID_Paciente_FK = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{pacienteId}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer findByProntuarioProntuarioId(Integer idFk) {
        String sql = "SELECT ID_Prontuario FROM Prontuario WHERE ID_Paciente_FK = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idFk}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
