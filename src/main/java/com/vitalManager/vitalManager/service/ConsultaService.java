package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.ConsultaDTO;
import com.vitalManager.vitalManager.model.ConsultaModel;
import com.vitalManager.vitalManager.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public ConsultaDTO findById(int id) {
        Optional<ConsultaModel> consultaModel = consultaRepository.findById(id);
        return consultaModel.map(this::mapToDTO).orElse(null);
    }

    public List<ConsultaDTO> findAll() {
        return consultaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void save(ConsultaDTO consultaDTO) {
        consultaRepository.save(mapToModel(consultaDTO));
    }

    public void update(ConsultaDTO consultaDTO) {
        consultaRepository.update(mapToModel(consultaDTO));
    }

    public void delete(int id) {
        consultaRepository.deleteById(id);
    }

    private ConsultaDTO mapToDTO(ConsultaModel consultaModel) {
        return new ConsultaDTO(
                consultaModel.getIdProntuario(),
                consultaModel.getIdMedico(),
                consultaModel.getData(),
                consultaModel.getValor(),
                consultaModel.getHora(),
                consultaModel.getStatus(),
                consultaModel.getObservacoes()
        );
    }

    private ConsultaModel mapToModel(ConsultaDTO consultaDTO) {
        ConsultaModel consulta = new ConsultaModel();
        consulta.setIdProntuario(consultaDTO.idProntuario());
        consulta.setIdMedico(consultaDTO.idMedico());
        consulta.setData(consultaDTO.data());
        consulta.setValor(consultaDTO.valor());
        consulta.setHora(consultaDTO.hora());
        consulta.setStatus(consultaDTO.status());
        consulta.setObservacoes(consultaDTO.observacoes());
        return consulta;
    }
}
