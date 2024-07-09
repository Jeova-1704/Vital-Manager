package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.TelefoneDTO;
import com.vitalManager.vitalManager.DTO.TelefoneFornecedorDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelefoneFornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private TelefoneFornecedorRepository telefoneFornecedorRepository;

    public List<TelefoneFornecedorModel> getAllPhones() {
        return telefoneFornecedorRepository.findAll();
    }

    public TelefoneFornecedorModel getPhoneByIdUser(int id) {
        return telefoneFornecedorRepository.findBySupplierId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum endereço atribuido ao usuario de id " + id));
    }

    public TelefoneFornecedorModel createPhone(TelefoneFornecedorDTO telefoneFornecedorDTO) {

        fornecedorRepository.findById(telefoneFornecedorDTO.id_fornecedor_fk())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum telefone atribuido ao fornecedor de id " + telefoneFornecedorDTO.id_fornecedor_fk()));

        TelefoneFornecedorModel telefoneFornecedorModel = convertDtoToModel(telefoneFornecedorDTO);
        telefoneFornecedorRepository.save(telefoneFornecedorModel);
        return telefoneFornecedorModel;
    }


    public TelefoneFornecedorModel convertDtoToModel(TelefoneFornecedorDTO body) {
        TelefoneFornecedorModel telefone = new TelefoneFornecedorModel();

        telefone.setIdFornecedor(body.id_fornecedor_fk());
        telefone.setTelefone(body.telefone());

        return telefone;
    }

    public TelefoneFornecedorModel updatePhone(int id, TelefoneFornecedorDTO telefoneFornecedorDTO) {
        if (!telefoneFornecedorRepository.existsBySupplierId(id)) {
            throw new ResourceNotFoundException("Nenhum telefone atribuido ao fornecedor de id " + id);
        }

        TelefoneFornecedorModel telefoneExistente = telefoneFornecedorRepository.findBySupplierId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum telefone atribuido ao fornecedor de id " + id));

        TelefoneFornecedorModel telefoneAtualizar = convertDtoToModel(telefoneFornecedorDTO);
        telefoneAtualizar.setIdTelefoneFornecedor(telefoneExistente.getIdTelefoneFornecedor());
        telefoneAtualizar.setIdFornecedor(telefoneExistente.getIdFornecedor());
        telefoneFornecedorRepository.update(telefoneAtualizar);
        return telefoneAtualizar;
    }

    public void deletePhoneByUserId(int id){
        if (! telefoneFornecedorRepository.existsBySupplierId(id)){
            throw new ResourceNotFoundException("Nenhum telefone atribuido ao fornecedor de id " + id);
        }
        telefoneFornecedorRepository.deleteBySupplierId(id);
    }


}