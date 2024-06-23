package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.PacienteDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.PacienteModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.PacienteRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PacienteModel> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public PacienteModel getPacienteById(int id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id " + id));
    }

    public PacienteModel createPaciente(PacienteDTO pacienteDTO) {
        UsuarioModel usuario = usuarioRepository.findById(pacienteDTO.idUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + pacienteDTO.idUsuario()));

        PacienteModel paciente = new PacienteModel();
        paciente.setUsuario(usuario);
        paciente.setNumeroProntuario(pacienteDTO.numeroProntuario());
        pacienteRepository.save(paciente);
        return paciente;
    }

    public PacienteModel updatePaciente(int id, PacienteModel pacienteDTO) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente not found with id " + id);
        }
        PacienteModel pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id " + id));

        pacienteExistente.setNumeroProntuario(pacienteDTO.getNumeroProntuario());
        pacienteRepository.update(pacienteExistente);
        return pacienteExistente;
    }

    public void deletePacienteById(int id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente not found with id " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
