package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EnderecoFornecedorDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import com.vitalManager.vitalManager.repository.EnderecoFornecedorRepository;
import com.vitalManager.vitalManager.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoFornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EnderecoFornecedorRepository enderecoFornecedorRepository;

    public List<EnderecoFornecedorModel> getAllAddresses() {
        return enderecoFornecedorRepository.findAll();
    }

    public EnderecoFornecedorModel getAddressBySupplierId(int id) {
        return enderecoFornecedorRepository.findBySupplierId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum endereço atribuido ao fornecedor de id " + id));
    }

    public EnderecoFornecedorModel createSupplierAddress(EnderecoFornecedorDTO enderecoFornecedorDTO) {

        fornecedorRepository.findById(enderecoFornecedorDTO.idFornecedor())
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id " + enderecoFornecedorDTO.idFornecedor()));

        EnderecoFornecedorModel enderecoFornecedor = convertDtoToModel(enderecoFornecedorDTO);
        enderecoFornecedorRepository.save(enderecoFornecedor);
        return enderecoFornecedor;
    }

    public EnderecoFornecedorModel updateSupplierAddress(int id, EnderecoFornecedorDTO enderecoFornecedorDTO) {
        if (!fornecedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fornecedor não encontrado com id " + id);
        }

        EnderecoFornecedorModel enderecoExistente = enderecoFornecedorRepository.findBySupplierId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço do fornecedor não encontrado com id " + id));

        EnderecoFornecedorModel enderecoAtualizar = convertDtoToModel(enderecoFornecedorDTO);
        enderecoAtualizar.setIdEnderecoFornecedor(enderecoExistente.getIdEnderecoFornecedor());
        enderecoFornecedorRepository.update(enderecoAtualizar);
        return enderecoAtualizar;
    }

    public EnderecoFornecedorModel convertDtoToModel(EnderecoFornecedorDTO body) {
        EnderecoFornecedorModel fornecedorEndereco = new EnderecoFornecedorModel();
        fornecedorEndereco.setCidade(body.cidade());
        fornecedorEndereco.setEstado(body.estado());
        fornecedorEndereco.setBairro(body.bairro());
        fornecedorEndereco.setCep(body.cep());
        fornecedorEndereco.setPais(body.pais());
        fornecedorEndereco.setRua(body.rua());
        fornecedorEndereco.setNumero(body.numero());
        fornecedorEndereco.setIdFornecedor(body.idFornecedor());
        return fornecedorEndereco;
    }

    public void deleteAddressBySupplierId(int id){
        if (! enderecoFornecedorRepository.existsBySupplierId(id)){
            throw new ResourceNotFoundException("Nenhum endereço atribuido ao fornecedor de id " + id);
        }
        enderecoFornecedorRepository.deleteBySupplierId(id);
    }
}
