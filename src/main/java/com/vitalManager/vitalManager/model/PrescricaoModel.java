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
public class PrescricaoModel {
    private Integer idPrescricao;
    private ConsultaModel consulta;
    private Integer idConsultaFk;
    private String instrucoes;
    private List<ItensHospitalaresModel> itensHospitalareModels;
}
