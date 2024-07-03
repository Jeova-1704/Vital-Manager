package com.vitalManager.vitalManager.DTO;

import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;

public record EnderecoUsuarioDTO(String cep , String rua, String bairro, String cidade, String estado,
                                 String pais, String numero_casa,Integer id_usuario_fk) {

    public EnderecoUsuarioDTO(EnderecoUsuarioModel enderecoUsuario) {
        this(enderecoUsuario.getCep(), enderecoUsuario.getRua(), enderecoUsuario.getBairro(),
                enderecoUsuario.getCidade(), enderecoUsuario.getEstado(), enderecoUsuario.getPais(),
                enderecoUsuario.getNumeroCasa(),enderecoUsuario.getIdUsuarioFK());
    }

}
