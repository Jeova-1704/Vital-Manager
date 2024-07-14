package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.ItensHospitalaresModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ItemHospitalarDTO(Integer idEstoque, Integer idPrescricao, String nome, BigDecimal preco, String descricao, LocalDate dataValidade, Integer quantidade, Integer idFornecedor) {

    public ItemHospitalarDTO(ItensHospitalaresModel itemHospitalar) {
        this(itemHospitalar.getIdEstoque(), itemHospitalar.getIdPrescricao(),itemHospitalar.getNome(), itemHospitalar.getPreco(), itemHospitalar.getDescricao(), itemHospitalar.getDataValidade(), itemHospitalar.getQuantidade(), itemHospitalar.getIDFornecedorFK());
    }
}
