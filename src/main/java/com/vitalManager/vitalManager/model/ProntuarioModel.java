package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioModel {
    private Integer idProntuario;
    private Integer idade;
    private String tipoSanguineo;
    private Float altura;
    private Float peso;
    private Float temperatura;
    private String presao;
    private String descricao;
    private LocalDateTime dataCriacao;
}
