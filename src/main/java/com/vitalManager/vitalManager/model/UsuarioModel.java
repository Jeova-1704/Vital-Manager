package com.vitalManager.vitalManager.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    protected Integer idUsuario;
    protected String nome;
    protected String sobrenome;
    protected String email;
    protected String senha;
    protected LocalDate dataNascimento;
    protected String sexo;
    protected LocalDateTime date;
    protected char tipo;
}
