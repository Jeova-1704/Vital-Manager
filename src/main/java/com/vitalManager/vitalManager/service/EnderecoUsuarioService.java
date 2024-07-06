package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.EnderecoUsuarioRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

<<<<<<< HEAD
    public EnderecoUsuarioModel updateEndereco(int id, EnderecoUsuarioDTO enderecoDTO) {
        if (!enderecoUsuarioRepository.existsByUserId(id)) {
            throw new ResourceNotFoundException("Endereço não encontrado com id " + id);
        }
        EnderecoUsuarioModel enderecoExistente = enderecoUsuarioRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com id " + id));

        enderecoExistente.setCep(enderecoDTO.cep());
        enderecoExistente.setRua(enderecoDTO.rua());
        enderecoExistente.setBairro(enderecoDTO.bairro());
        enderecoExistente.setCidade(enderecoDTO.cidade());
        enderecoExistente.setEstado(enderecoDTO.estado());
        enderecoExistente.setPais(enderecoDTO.pais());
        enderecoExistente.setNumeroCasa(enderecoDTO.numero_casa());
        enderecoExistente.setIdUsuarioFK(enderecoDTO.id_usuario_fk());

        enderecoUsuarioRepository.update(enderecoExistente);
        return enderecoExistente;
    }


=======
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




>>>>>>> e72f4fea0908b8a5b57187ac40a98345af28e779
    public void deleteAdressByUserId(int id){
        if (! enderecoUsuarioRepository.existsByUserId(id)){
            throw new ResourceNotFoundException("Nenhum endereço atribuido ao usuario de id " + id);
        }
        enderecoUsuarioRepository.deleteByUserId(id);
    }


}