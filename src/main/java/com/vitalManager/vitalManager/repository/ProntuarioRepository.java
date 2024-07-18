package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.ProntuarioModel;

import java.util.List;
import java.util.Optional;

public interface ProntuarioRepository {

    List<ProntuarioModel> findAll();
    Optional<ProntuarioModel> findById(int id);
    int save(ProntuarioModel prontuario);
    int update(ProntuarioModel prontuario);
    int deleteById(int id);
    boolean existsById(int id);
    Integer findProntuarioIdByUsuarioId(int usuarioId);

    Integer findByProntuarioProntuarioId(Integer idFk);
}
