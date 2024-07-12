package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.PacienteModel;

import java.time.LocalDate;

public record PacienteDTO(Integer idUsuario, Integer numeroProntuario) {

    public PacienteDTO(PacienteModel pacienteModel) {
        this(pacienteModel.getIdUsuarioFK(),pacienteModel.getNumeroProntuario());
    }
}
