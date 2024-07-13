package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.PacienteModel;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {
    List<PacienteModel> findAll();
    Optional<PacienteModel> findById(int id);
    int save(PacienteModel paciente);
    int update(PacienteModel paciente);
    int deleteById(int id);
    boolean existsById(int id);
    Integer findPacienteIdByUsuarioId(int idUsuario);
}
