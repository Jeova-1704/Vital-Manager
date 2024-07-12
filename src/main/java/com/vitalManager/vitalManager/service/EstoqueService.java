package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.EstoqueDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EstoqueModel;
import com.vitalManager.vitalManager.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public List<EstoqueModel> getAllEstoques() {
        return estoqueRepository.findAll();
    }

    public EstoqueModel getEstoqueById(int id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque not found with id " + id));
    }

    public EstoqueModel createEstoque(EstoqueDTO estoqueDTO) {
        EstoqueModel estoque = convertDtoToModel(estoqueDTO);
        estoqueRepository.save(estoque);
        return estoque;
    }

    public EstoqueModel updateEstoque(int id, EstoqueDTO estoqueDTO) {
        if (!estoqueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estoque not found with id " + id);
        }
        EstoqueModel estoqueExistente = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque not found with id " + id));
        EstoqueModel estoqueAtualizar = convertDtoToModel(estoqueDTO);
        estoqueAtualizar.setIdEstoque(estoqueExistente.getIdEstoque());
        estoqueRepository.update(estoqueAtualizar);
        return estoqueAtualizar;
    }

    public void deleteEstoqueById(int id) {
        if (!estoqueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estoque not found with id " + id);
        }
        estoqueRepository.deleteById(id);
    }

    public EstoqueModel convertDtoToModel(EstoqueDTO estoqueDTO) {
        EstoqueModel estoque = new EstoqueModel();
        estoque.setNome(estoqueDTO.nome());
        estoque.setDataAtualizacao(estoqueDTO.data_atualizacao());
        estoque.setQuantidade(estoqueDTO.quantidade());
        return estoque;
    }
}
