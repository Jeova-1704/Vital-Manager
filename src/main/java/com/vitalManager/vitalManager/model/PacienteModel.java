package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteModel {
    private Integer idPaciente;
    private UsuarioModel usuario;
    private String numeroProntuario;
}
