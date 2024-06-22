package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    List<UsuarioModel> findAll();
    Optional<UsuarioModel> findById(int id);
    int save(UsuarioModel usuario);
    int update(UsuarioModel usuario);
    int deleteById(int id);
    boolean existsById(int id);
    Optional<UsuarioModel> findByEmail(String email);
}
