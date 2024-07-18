package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.ExameModel;

import java.util.List;
import java.util.Optional;

public interface ExameRepository {
    List<ExameModel> findAll();
    Optional<ExameModel> findById(int id);
    int save(ExameModel exame);
    int update(ExameModel exame);
    int deleteById(int id);
    boolean existsById(int id);
    List<ExameModel> getExamesByPacienteId(int id);
}
