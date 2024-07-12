package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EstoqueDTO;
import com.vitalManager.vitalManager.DTO.FornecedorDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.FornecedorModel;
import com.vitalManager.vitalManager.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<FornecedorModel> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public FornecedorModel getFornecedorById(int id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor not found with id " + id));
    }

    public FornecedorModel createFornecedor(FornecedorDTO fornecedorDTO) {
        FornecedorModel fornecedor = convertDtoToModel(fornecedorDTO);
        fornecedorRepository.save(fornecedor);
        return fornecedor;
    }

    public FornecedorModel updateFornecedor(int id, FornecedorDTO fornecedorDTO) {
        if (!fornecedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor not found with id " + id);
        }
        FornecedorModel fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor not found with id " + id));
        FornecedorModel fornecedorAtualizar = convertDtoToModel(fornecedorDTO);
        fornecedorAtualizar.setIdFornecedor(fornecedorExistente.getIdFornecedor());
        fornecedorRepository.update(fornecedorAtualizar);
        return fornecedorAtualizar;
    }

    public void deleteFornecedorById(int id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor not found with id " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    public FornecedorModel convertDtoToModel(FornecedorDTO fornecedorDTO) {
        FornecedorModel fornecedor = new FornecedorModel();
        fornecedor.setIdFornecedor(fornecedorDTO.idFornecedor());
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        return fornecedor;
    }
}
