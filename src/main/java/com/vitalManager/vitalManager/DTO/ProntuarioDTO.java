package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.ProntuarioModel;

public record ProntuarioDTO(Integer idProntuario, Integer idPacienteFK, Integer idade, String tipoSanguineo, Float altura, Float peso, Float temperatura, String pressao, String descricao) {

    public ProntuarioDTO(ProntuarioModel prontuario) {
        this(prontuario.getIdProntuario() ,prontuario.getIdPacienteFK(), prontuario.getIdade(), prontuario.getTipoSanguineo(), prontuario.getAltura(), prontuario.getPeso(), prontuario.getTemperatura(), prontuario.getPressao(), prontuario.getDescricao());
    }
}
