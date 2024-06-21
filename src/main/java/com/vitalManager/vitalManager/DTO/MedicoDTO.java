package com.vitalManager.vitalManager.DTO;

import java.math.BigDecimal;

public record MedicoDTO(int idUsuario, BigDecimal salario, String especialidade, String CRM) {
}