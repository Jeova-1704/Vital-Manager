package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoModel extends UsuarioModel{
    private BigDecimal Salario;
    private String especialidade;
    private String CRM;
    private LocalDateTime data_contratacao;
}
