package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaModel {
    private Integer idConsulta;
    private Integer idProntuario;
    private Integer idMedico;
    private Timestamp data;
    private BigDecimal valor;
    private Time hora;
    private String status;
    private String observacoes;
}