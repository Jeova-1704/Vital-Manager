package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoFornecedorModel {
    private Integer idEnderecoFornecedor;
    private Integer idFornecedor;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String pais;
    private String estado;
    private String numero;
}
