package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.PrescricaoDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ConsultaModel;
import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import com.vitalManager.vitalManager.model.PrescricaoModel;
import com.vitalManager.vitalManager.repository.PrescricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

    private final PrescricaoRepository prescricaoRepository;

    public List<PrescricaoModel> getAllPrescricoes() {
        return prescricaoRepository.findAll();
    }

    public PrescricaoModel getPrescricaoById(int id) {
        return prescricaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescricao not found with id " + id));
    }

    public PrescricaoModel createPrescricao(PrescricaoDTO prescricaoDTO) {
        PrescricaoModel prescricao = convertDtoToModel(prescricaoDTO);
        prescricaoRepository.save(prescricao);
        return prescricao;
    }

    public PrescricaoModel updatePrescricao(int id, PrescricaoDTO prescricaoDTO) {
        if (!prescricaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prescricao not found with id " + id);
        }
        PrescricaoModel prescricaoExistente = prescricaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescricao not found with id " + id));
        PrescricaoModel prescricaoAtualizar = convertDtoToModel(prescricaoDTO);
        prescricaoAtualizar.setIdPrescricao(prescricaoExistente.getIdPrescricao());
        prescricaoRepository.update(prescricaoAtualizar);
        return prescricaoAtualizar;
    }

    public void deletePrescricaoById(int id) {
        if (!prescricaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prescricao not found with id " + id);
        }
        prescricaoRepository.deleteById(id);
    }

    private PrescricaoModel convertDtoToModel(PrescricaoDTO prescricaoDTO) {
        PrescricaoModel prescricao = new PrescricaoModel();
        prescricao.setIdPrescricao(prescricaoDTO.idPrescricao());
        prescricao.setIdConsultaFk(prescricaoDTO.idConsulta());
        prescricao.setInstrucoes(prescricaoDTO.instrucoes());
        return prescricao;
    }
}
