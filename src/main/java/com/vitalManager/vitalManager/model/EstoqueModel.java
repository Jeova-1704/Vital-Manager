package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueModel {
    private Integer idEstoque;
    private String nome;
    private LocalDate dataAtualizacao;
    private Integer quantidade;
    private List<ItensHospitalaresModel> itensHospitalareModels;

}
