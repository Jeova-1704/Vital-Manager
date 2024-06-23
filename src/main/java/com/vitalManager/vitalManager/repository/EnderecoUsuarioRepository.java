package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;

import java.util.List;
import java.util.Optional;

public interface EnderecoUsuarioRepository {
    List<EnderecoUsuarioModel> findAll();
    Optional<EnderecoUsuarioModel> findByUserId(int id);
    int save(EnderecoUsuarioModel enderecoUsuarioModel);
    int update(EnderecoUsuarioModel enderecoUsuarioModel);
    int deleteAdressByUserId(int id);
    boolean existsById(int id);
}
