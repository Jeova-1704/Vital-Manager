package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.ExameDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ExameModel;
import com.vitalManager.vitalManager.repository.ExameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExameService {

    private final ExameRepository exameRepository;

    public List<ExameModel> getAllExames() {
        return exameRepository.findAll();
    }

    public ExameModel getExameById(int id) {
        return exameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame not found with id " + id));
    }

    public ExameModel createExame(ExameDTO exameDTO) {
        ExameModel exame = convertDtoToModel(exameDTO);
        exameRepository.save(exame);
        return exame;
    }

    public ExameModel updateExame(int id, ExameDTO exameDTO) {
        if (!exameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exame not found with id " + id);
        }
        ExameModel exameExistente = exameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame not found with id " + id));
        ExameModel exameAtualizar = convertDtoToModel(exameDTO);
        exameAtualizar.setIdExame(exameExistente.getIdExame());
        exameRepository.update(exameAtualizar);
        return exameAtualizar;
    }

    public void deleteExameById(int id) {
        if (!exameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exame not found with id " + id);
        }
        exameRepository.deleteById(id);
    }

    public ExameModel convertDtoToModel(ExameDTO exameDTO) {
        ExameModel exame = new ExameModel();
        exame.setIdPaciente(exameDTO.idPaciente());
        exame.setTipoExame(exameDTO.tipoExame());
        exame.setResultado(exameDTO.resultado());
        exame.setDataExame(exameDTO.dataExame());
        return exame;
    }
}
