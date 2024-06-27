package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExameModel {
    private Integer idExame;
    private Integer idPaciente;
    private String tipoExame;
    private String resultado;
    private LocalDate dataExame;
}
