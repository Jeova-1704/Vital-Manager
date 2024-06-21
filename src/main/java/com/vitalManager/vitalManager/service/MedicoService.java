package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.MedicoDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.MedicoModel;
import com.vitalManager.vitalManager.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<MedicoModel> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public MedicoModel getMedicoById(int id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico not found with id " + id));
    }

    public void createMedico(MedicoDTO medicoDTO) {
        MedicoModel medico = new MedicoModel();
        medico.setNome(medicoDTO.nome());
        medico.setSobrenome(medicoDTO.sobrenome());
        medico.setEmail(medicoDTO.email());
        medico.setSenha(medicoDTO.senha());
        medico.setDataNascimento(medicoDTO.dataNascimento());
        medico.setSexo(medicoDTO.sexo());
        medico.setDate(LocalDateTime.now());
        medico.setSalario(medicoDTO.salario());
        medico.setEspecialidade(medicoDTO.especialidade());
        medico.setCRM(medicoDTO.CRM());
        medico.setData_contratacao(LocalDateTime.now());
        medicoRepository.save(medico);
    }

    public void updateMedico(int id, MedicoModel medicoDTO) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medico not found with id " + id);
        }
        MedicoModel medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico not found with id " + id));
        medicoExistente.setNome(medicoDTO.getNome());
        medicoExistente.setSobrenome(medicoDTO.getSobrenome());
        medicoExistente.setEmail(medicoDTO.getEmail());
        medicoExistente.setSenha(medicoDTO.getSenha());
        medicoExistente.setDataNascimento(medicoDTO.getDataNascimento());
        medicoExistente.setSexo(medicoDTO.getSexo());
        medicoExistente.setDate(LocalDateTime.now());
        medicoExistente.setSalario(medicoDTO.getSalario());
        medicoExistente.setEspecialidade(medicoDTO.getEspecialidade());
        medicoExistente.setCRM(medicoDTO.getCRM());
        medicoExistente.setData_contratacao(LocalDateTime.now());

        medicoRepository.save(medicoExistente);
    }

    public void deleteMedicoById(int id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medico not found with id " + id);
        }
        medicoRepository.deleteById(id);
    }
}
