package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.ItemHospitalarDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import com.vitalManager.vitalManager.repository.ItensHospitalaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemHospitalarService {

    @Autowired
    private ItensHospitalaresRepository repository;

    
    public List<ItensHospitalaresModel> getAllItemHospitalar() {
        return repository.findAll();
    }

    public ItensHospitalaresModel getItemHospitalarById(int id) {
        Optional<ItensHospitalaresModel> item = repository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new ResourceNotFoundException("Item not found with id " + id);
        }
    }

    public ItensHospitalaresModel createItemHospitalar(ItemHospitalarDTO itemHospitalarDTO) {
        ItensHospitalaresModel item = converterDTOToModel(itemHospitalarDTO);
        return repository.create(item);
    }

    
    public ItensHospitalaresModel updateItemHospitalar(int id, ItemHospitalarDTO itemHospitalarDTO) {
        Optional<ItensHospitalaresModel> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            ItensHospitalaresModel existingItem = existingItemOptional.get();
            existingItem.setNome(itemHospitalarDTO.nome());
            existingItem.setPreco(itemHospitalarDTO.preco());
            existingItem.setDescricao(itemHospitalarDTO.descricao());
            existingItem.setDataValidade(itemHospitalarDTO.dataValidade());
            existingItem.setQuantidade(itemHospitalarDTO.quantidade());
            existingItem.setIdPrescricao(itemHospitalarDTO.idPrescricao());
            existingItem.setIdEstoque(itemHospitalarDTO.idEstoque());
            existingItem.setIDFornecedorFK(itemHospitalarDTO.idFornecedor());
            return repository.update(existingItem);
        } else {
            throw new ResourceNotFoundException("Item not found with id " + id);
        }
    }

    
    public boolean deleteItemHospitar(int id) {
        Optional<ItensHospitalaresModel> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ItensHospitalaresModel converterDTOToModel(ItemHospitalarDTO itemHospitalarDTO) {
        ItensHospitalaresModel item = new ItensHospitalaresModel();
        item.setNome(itemHospitalarDTO.nome());
        item.setPreco(itemHospitalarDTO.preco());
        item.setDescricao(itemHospitalarDTO.descricao());
        item.setDataValidade(itemHospitalarDTO.dataValidade());
        item.setQuantidade(itemHospitalarDTO.quantidade());
        item.setIdPrescricao(itemHospitalarDTO.idPrescricao());
        item.setIdEstoque(itemHospitalarDTO.idEstoque());
        item.setIDFornecedorFK(itemHospitalarDTO.idFornecedor());
        return item;

    }
}
