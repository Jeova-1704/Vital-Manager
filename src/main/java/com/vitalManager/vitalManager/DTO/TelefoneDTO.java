package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.TelefoneModel;

public record TelefoneDTO(Integer id_usuario_fk,String contato,String tipo) {

    public TelefoneDTO(TelefoneModel telefoneModel) {
        this(telefoneModel.getIdUsuarioFK(),telefoneModel.getContato(),
                telefoneModel.getTipo());
    }

}
