package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import com.vitalManager.vitalManager.model.TelefoneModel;

public record TelefoneFornecedorDTO(Integer id_fornecedor_fk, String telefone) {

    public TelefoneFornecedorDTO(TelefoneFornecedorModel telefoneFornecedorModel){
        this(telefoneFornecedorModel.getIdFornecedor(),telefoneFornecedorModel.getTelefone());
    }


}
