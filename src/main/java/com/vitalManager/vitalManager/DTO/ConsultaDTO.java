package com.vitalManager.vitalManager.DTO;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public record ConsultaDTO(
        Integer idConsulta,
        Integer idProntuario,
        Integer idMedico,
        Timestamp data,
        BigDecimal valor,
        Time hora,
        String status,
        String observacoes
) {}
