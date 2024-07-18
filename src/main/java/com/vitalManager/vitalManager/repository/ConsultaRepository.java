package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.ConsultaModel;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository {
    List<ConsultaModel> findAll();
    Optional<ConsultaModel> findById(int id);
    int save(ConsultaModel consulta);
    int update(ConsultaModel consulta);
    int deleteById(int id);
    boolean existsById(int id);
    List<ConsultaModel> getConsultasByProntuarioId(int id);
    List<ConsultaModel> getConsultasByMedicoId(int medicoId);
}
