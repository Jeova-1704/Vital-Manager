package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;

import java.util.List;
import java.util.Optional;

public interface TelefoneRepository {
    List<TelefoneModel> findAll();
    Optional<TelefoneModel> findByPhoneId(int id);
    List<TelefoneModel> getTelefonesByUserId(int id);
    int save(TelefoneModel telefoneModel);
    int update(TelefoneModel telefoneModel);
    int deleteByUserId(int id);
    boolean existsByUserId(int id);
}
