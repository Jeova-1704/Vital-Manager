package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.AdminModel;
import com.vitalManager.vitalManager.model.EstoqueModel;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository {
    List<EstoqueModel> findAll();
    Optional<EstoqueModel> findById(int id);
    int save(EstoqueModel admin);
    int update(EstoqueModel admin);
    int deleteById(int id);
    boolean existsById(int id);
}
