package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.TelefoneDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.EnderecoUsuarioRepository;
import com.vitalManager.vitalManager.repository.TelefoneRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TelefoneService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    public List<TelefoneModel> getAllPhones() {
        return telefoneRepository.findAll();
    }

    public Optional<TelefoneModel> getPhoneById(int id) {
        return telefoneRepository.findByPhoneId(id);
    }

    public TelefoneModel createPhone(TelefoneDTO telefoneDTO) {

        usuarioRepository.findById(telefoneDTO.id_usuario_fk())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + telefoneDTO.id_usuario_fk()));

        TelefoneModel telefoneModel = convertDtoToModel(telefoneDTO);
        telefoneRepository.save(telefoneModel);
        return telefoneModel;
    }


    public TelefoneModel convertDtoToModel(TelefoneDTO body) {
        TelefoneModel telefone = new TelefoneModel();
        telefone.setIdUsuarioFK(body.id_usuario_fk());
        telefone.setTipo(body.tipo());
        telefone.setContato(body.contato());

        return telefone;
    }

    public TelefoneModel updatePhone(int id, TelefoneDTO telefoneDTO) {
        if (!telefoneRepository.existsByUserId(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }


        if (telefoneRepository.findByPhoneId(id).isEmpty()){
            throw new ResourceNotFoundException("Phone not found with id " + id);
        }

        TelefoneModel telefoneExistente = telefoneRepository.findByPhoneId(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado com ID:" + id));

        TelefoneModel telefoneAtualizar = convertDtoToModel(telefoneDTO);
        telefoneAtualizar.setIdTelefone(telefoneExistente.getIdTelefone());
        telefoneRepository.update(telefoneAtualizar);
        return telefoneAtualizar;
    }

    public void deletePhoneByUserId(int id){
        if (! telefoneRepository.existsByUserId(id)){
            throw new ResourceNotFoundException("Nenhum telefone atribuido ao usuario de id " + id);
        }
        telefoneRepository.deleteByUserId(id);
    }


}