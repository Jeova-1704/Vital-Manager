package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.repository.EnderecoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoUsuarioService {


    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    public List<EnderecoUsuarioModel> getAllAdress() {
        return enderecoUsuarioRepository.findAll();
    }

    public EnderecoUsuarioModel getAdressByIdUser(int id) {
        return enderecoUsuarioRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adress not found with id " + id));
    }

    public EnderecoUsuarioModel createUsuarioAdress(EnderecoUsuarioDTO enderecoUsuarioDTO) {
        EnderecoUsuarioModel enderecoUsuario = new EnderecoUsuarioModel();
        enderecoUsuario.setCep(enderecoUsuarioDTO.cep());
        enderecoUsuario.setRua(enderecoUsuarioDTO.rua());
        enderecoUsuario.setBairro(enderecoUsuarioDTO.bairro());
        enderecoUsuario.setCidade(enderecoUsuarioDTO.cidade());
        enderecoUsuario.setEstado(enderecoUsuarioDTO.estado());
        enderecoUsuario.setPais(enderecoUsuarioDTO.pais());
        enderecoUsuario.setNumeroCasa(enderecoUsuarioDTO.numero_casa());
        enderecoUsuario.setIdUsuarioFK(enderecoUsuarioDTO.id_usuario_fk());
        enderecoUsuarioRepository.save(enderecoUsuario);
        return enderecoUsuario;
    }

    public void deleteAdressByUserId(int id){
        if (! enderecoUsuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Adress not found with User Id " + id);
        }
        enderecoUsuarioRepository.deleteAdressByUserId(id);
    }


}
