package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoModel {
    private Integer idMedico;
    private UsuarioModel usuario;
    private BigDecimal salario;
    private String especialidade;
    private String crm;
    private LocalDateTime dataContratacao;
    private List<ConsultaModel> consultas;
}
