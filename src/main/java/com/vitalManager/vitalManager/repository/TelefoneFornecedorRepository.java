package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;

import java.util.List;
import java.util.Optional;

public interface TelefoneFornecedorRepository {
    List<TelefoneFornecedorModel> findAll();
    Optional<TelefoneFornecedorModel> findBySupplierId(int id);
    int save(TelefoneFornecedorModel telefoneFornecedorModel);
    int update(TelefoneFornecedorModel telefoneFornecedorModel);
    int deleteBySupplierId(int id);
    boolean existsBySupplierId(int id);
}
