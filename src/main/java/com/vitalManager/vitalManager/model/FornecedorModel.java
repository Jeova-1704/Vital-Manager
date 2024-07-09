package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorModel {
    private Integer idFornecedor;
    private String nome;
    private String cnpj;
    private EnderecoFornecedorModel enderecoFornecedorModel;
    private List<TelefoneFornecedorModel> telefonesFornecedor;
}
