package com.vitalManager.vitalManager.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String sexo;
    private LocalDateTime date;
    private char tipo;
}
