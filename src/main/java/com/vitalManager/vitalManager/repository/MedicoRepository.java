package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.MedicoModel;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    List<MedicoModel> findAll();
    Optional<MedicoModel> findById(int id);
    Optional<MedicoModel> findByUsuarioEmail(String email);
    int save(MedicoModel medico);
    int update(MedicoModel medico);
    int deleteById(int id);
    boolean existsById(int id);
    Integer findMedicoIdByUsuarioId(int idUsuario);
}