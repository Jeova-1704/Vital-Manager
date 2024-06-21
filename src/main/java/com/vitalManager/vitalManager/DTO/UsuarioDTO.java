package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.UsuarioModel;

import java.time.LocalDate;

public record UsuarioDTO(String nome, String sobrenome, String email, String senha,
                         LocalDate dataNascimento, String sexo) {

    public UsuarioDTO(UsuarioModel usuario) {
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo());
    }
}
