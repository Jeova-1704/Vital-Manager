package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteModel {
    private Integer idPaciente;
    private UsuarioModel usuario;
    private Integer idUsuarioFk;
    private Integer numeroProntuario;
    private ProntuarioModel prontuario;
    private List<ExameModel> exames;
}
