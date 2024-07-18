package com.vitalManager.vitalManager.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", tipo='" + tipo + '\'' +
                ", enderecoUsuario=" + enderecoUsuario +
                ", telefoneUsuario=" + telefoneUsuario +
                '}';
    }
}
