package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensHospitalaresModel {
    private Integer idItensHospitalares;
    private Integer idEstoque;
    private Integer idPrescricao;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private LocalDate dataValidade;
    private Integer quantidade;
}
