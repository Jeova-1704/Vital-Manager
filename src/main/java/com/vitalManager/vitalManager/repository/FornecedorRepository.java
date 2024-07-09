package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.FornecedorModel;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository {
    List<FornecedorModel> findAll();
    Optional<FornecedorModel> findById(int id);
    int save(FornecedorModel fornecedor);
    int update(FornecedorModel fornecedor);
    int deleteById(int id);
    boolean existsById(int id);
}