package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoUsuarioModel {
    private Integer idEnderecoUsuario;
    private Integer idUsuarioFK;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String numeroCasa;
}
