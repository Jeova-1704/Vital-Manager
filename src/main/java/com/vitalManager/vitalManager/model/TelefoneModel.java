package com.vitalManager.vitalManager.model;

import com.vitalManager.vitalManager.model.Enum.TipoTelefoneEnum;
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
    private TipoTelefoneEnum tipo;
}
