package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.FornecedorModel;

public record FornecedorDTO(
        Integer idFornecedor,
        String nome,
        String cnpj
) {
    public FornecedorDTO(FornecedorModel fornecedorModel) {
        this(
                fornecedorModel.getIdFornecedor(),
                fornecedorModel.getNome(),
                fornecedorModel.getCnpj()
        );
    }
}
