package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.EstoqueModel;

import java.time.LocalDate;

public record EstoqueDTO(Integer quantidade, String nome, LocalDate data_atualizacao) {

    public EstoqueDTO(EstoqueModel estoqueModel) {
        this(estoqueModel.getQuantidade(),estoqueModel.getNome(),estoqueModel.getDataAtualizacao());
    }

}
