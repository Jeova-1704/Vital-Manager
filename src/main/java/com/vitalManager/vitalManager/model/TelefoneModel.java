package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneModel {
    private Integer idTelefone;
    private Integer IdUsuarioFK;
    private String contato;
    private String tipo;
}
