package com.vitalManager.vitalManager.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String sexo;
    private LocalDateTime dataCriacao;
    private String tipo;
    private EnderecoUsuarioModel enderecoUsuario;
    private List<TelefoneModel> telefoneUsuario;
}
