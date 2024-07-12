package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.ExameModel;

import java.time.LocalDate;

public record ExameDTO(Integer idPaciente, String tipoExame, String resultado, LocalDate dataExame) {

    public ExameDTO(ExameModel exameModel) {
        this(exameModel.getIdPaciente(), exameModel.getTipoExame(), exameModel.getResultado(), exameModel.getDataExame());
    }
}
