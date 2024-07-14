package com.vitalManager.vitalManager.DTO;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Time;

public record ConsultaDTO(
        Integer idConsulta,
        Integer idProntuario,
        Integer idMedico,
        Data data,
        BigDecimal valor,
        Time hora,
        String status,
        String observacoes
) {}
