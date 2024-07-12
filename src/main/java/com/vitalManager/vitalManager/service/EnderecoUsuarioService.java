package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.repository.EnderecoUsuarioRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    public List<EnderecoUsuarioModel> getAllAdress() {
        return enderecoUsuarioRepository.findAll();
    }

    public EnderecoUsuarioModel getAdressByIdUser(int id) {
        return enderecoUsuarioRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum endereço atribuido ao usuario de id " + id));
    }

    public EnderecoUsuarioModel createUsuarioAdress(EnderecoUsuarioDTO enderecoUsuarioDTO) {

        usuarioRepository.findById(enderecoUsuarioDTO.id_usuario_fk())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + enderecoUsuarioDTO.id_usuario_fk()));

        EnderecoUsuarioModel enderecoUsuario = convertDtoToModel(enderecoUsuarioDTO);
        enderecoUsuarioRepository.save(enderecoUsuario);
        return enderecoUsuario;
    }

    public EnderecoUsuarioModel updateUserAdress(int id, EnderecoUsuarioDTO enderecoUsuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }

        EnderecoUsuarioModel enderecoExistente = enderecoUsuarioRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Adress not found with id " + id));

        EnderecoUsuarioModel enderecoAtualizar = convertDtoToModel(enderecoUsuarioDTO);
        enderecoAtualizar.setIdEnderecoUsuario(enderecoExistente.getIdEnderecoUsuario());
        System.out.println(enderecoAtualizar.getIdEnderecoUsuario());
        enderecoUsuarioRepository.update(enderecoAtualizar);
        return enderecoAtualizar;
    }

    public EnderecoUsuarioModel convertDtoToModel(EnderecoUsuarioDTO body) {
        EnderecoUsuarioModel userAdress = new EnderecoUsuarioModel();
        userAdress.setCidade(body.cidade());
        userAdress.setEstado(body.estado());
        userAdress.setBairro(body.bairro());
        userAdress.setCep(body.cep());
        userAdress.setPais(body.pais());
        userAdress.setRua(body.rua());
        userAdress.setNumeroCasa(body.numero_casa());
        userAdress.setIdUsuarioFK(body.id_usuario_fk());
        return userAdress;
    }

    public void deleteAdressByUserId(int id){
        if (! enderecoUsuarioRepository.existsByUserId(id)){
            throw new ResourceNotFoundException("Nenhum endereço atribuido ao usuario de id " + id);
        }
        enderecoUsuarioRepository.deleteByUserId(id);
    }


}