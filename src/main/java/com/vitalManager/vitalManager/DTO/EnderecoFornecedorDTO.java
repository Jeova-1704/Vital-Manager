package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;

public record EnderecoFornecedorDTO(String cep, String rua, String bairro, String cidade, String estado,
                                    String pais, String numero, Integer idFornecedor) {

    public EnderecoFornecedorDTO(EnderecoFornecedorModel enderecoFornecedor) {
        this(enderecoFornecedor.getCep(), enderecoFornecedor.getRua(), enderecoFornecedor.getBairro(),
                enderecoFornecedor.getCidade(), enderecoFornecedor.getEstado(), enderecoFornecedor.getPais(),
                enderecoFornecedor.getNumero(), enderecoFornecedor.getIdFornecedor());
    }

}
