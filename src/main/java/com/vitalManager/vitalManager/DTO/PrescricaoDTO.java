package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.ConsultaModel;
import com.vitalManager.vitalManager.model.PrescricaoModel;

public record PrescricaoDTO(Integer idPrescricao, Integer idConsulta, String instrucoes) {

    public PrescricaoDTO(PrescricaoModel prescricaoModel) {
        this(prescricaoModel.getIdPrescricao(),
                prescricaoModel.getIdConsultaFk(),
                prescricaoModel.getInstrucoes());
    }
}
