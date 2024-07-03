package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.UsuarioModel;

import java.time.LocalDate;

public record UsuarioDTO(String nome, String sobrenome, String CPF, String email, String senha,
                         LocalDate dataNascimento, String sexo, String tipo) {

    public UsuarioDTO(UsuarioModel usuario) {
        this(usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getEmail(),
                usuario.getSenha(), usuario.getDataNascimento(), usuario.getSexo(), usuario.getTipo());
    }
}
