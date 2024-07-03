package com.vitalManager.vitalManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneFornecedor {
    private Integer idTelefoneFornecedor;
    private Integer idFornecedor;
    private String telefone;
}
