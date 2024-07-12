package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.MedicoDTO;
import com.vitalManager.vitalManager.exception.NotTypeMedicoException;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.MedicoModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.MedicoRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<MedicoModel> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public MedicoModel getMedicoById(int id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico not found with id " + id));
    }

    public MedicoModel createMedico(MedicoDTO medicoDTO) {
        UsuarioModel usuario = usuarioRepository.findById(medicoDTO.idUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + medicoDTO.idUsuario()));
        if (usuario.getTipo().equals("M") || usuario.getTipo().equals("Medico")) {
            MedicoModel medico = new MedicoModel();
            medico.setUsuario(usuario);
            medico.setSalario(medicoDTO.salario());
            medico.setEspecialidade(medicoDTO.especialidade());
            medico.setCrm(medicoDTO.CRM());
            medico.setDataContratacao(LocalDateTime.now());
            medicoRepository.save(medico);
            medico = medicoRepository.findByUsuarioEmail(usuario.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("erro ao salvar médico no sistema"));
            return medico;

        } else {
            throw new NotTypeMedicoException("O usuário nao é do tipo médico.");
        }
    }

    public MedicoModel updateMedico(int id, MedicoDTO medicoDTO) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medico not found with id " + id);
        }
        MedicoModel medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico not found with id " + id));

        medicoExistente.setSalario(medicoDTO.salario());
        medicoExistente.setEspecialidade(medicoDTO.especialidade());
        medicoExistente.setCrm(medicoDTO.CRM());
        medicoExistente.setDataContratacao(LocalDateTime.now());

        medicoRepository.update(medicoExistente);
        return medicoExistente;
    }

    public void deleteMedicoById(int id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medico not found with id " + id);
        }
        medicoRepository.deleteById(id);
    }
}
