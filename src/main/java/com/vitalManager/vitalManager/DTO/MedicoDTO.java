package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.MedicoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicoDTO(String nome, String sobrenome, String email, String senha,
                        LocalDate dataNascimento, String sexo, BigDecimal salario,
                        String especialidade, String CRM, LocalDateTime data_contratacao) {

    public MedicoDTO(MedicoModel medico) {
        this(medico.getNome(), medico.getSobrenome(), medico.getEmail(),
                medico.getSenha(), medico.getDataNascimento(), medico.getSexo(),
                medico.getSalario(), medico.getEspecialidade(), medico.getCRM(),
                medico.getData_contratacao());
    }


}
