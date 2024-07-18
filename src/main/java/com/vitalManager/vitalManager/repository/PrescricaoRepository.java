package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.PrescricaoModel;

import java.util.List;
import java.util.Optional;

public interface PrescricaoRepository {
    List<PrescricaoModel> findAll();
    Optional<PrescricaoModel> findById(int id);
    int save(PrescricaoModel paciente);
    int update(PrescricaoModel paciente);
    int deleteById(int id);
    boolean existsById(int id);
}
