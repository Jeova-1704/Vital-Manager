package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.ProntuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.exception.UserTypeNotValidException;
import com.vitalManager.vitalManager.model.ProntuarioModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.ProntuarioRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public List<ProntuarioModel> getAllProntuario() {
        return prontuarioRepository.findAll();
    }

    public ProntuarioModel getProntuarioById(int id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuario not found with id " + id));
    }

    public ProntuarioModel createProntuario(ProntuarioDTO prontuarioDTO) {
        UsuarioModel usuario = usuarioRepository.findById(prontuarioDTO.idPacienteFK())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + prontuarioDTO.idPacienteFK()));

        if (usuario.getTipo().equals("P")) {
            ProntuarioModel prontuario = convertDTOToModel(prontuarioDTO);
            prontuario.setDataCriacao(LocalDateTime.now());
            prontuarioRepository.save(prontuario);
            return prontuario;
        } else {
            throw new UserTypeNotValidException("O usuario não tem permição para criação do prontuario");
        }
    }

    public ProntuarioModel updateProntuario(int id, ProntuarioDTO prontuarioDTO) {
        if (!prontuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prontuario not found with id " + id);
        }
        ProntuarioModel prontuario = convertDTOToModel(prontuarioDTO);
        prontuario.setIdProntuario(id);
        prontuario.setDataCriacao(LocalDateTime.now());
        prontuarioRepository.update(prontuario);
        return prontuario;
    }

    public boolean deleteProntuario(int id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prontuario not found with id " + id);
        }
        prontuarioRepository.deleteById(id);
        return true;
    }

    private ProntuarioModel convertDTOToModel(ProntuarioDTO prontuarioDTO) {
        ProntuarioModel prontuario = new ProntuarioModel();
        prontuario.setIdPacienteFK(prontuarioDTO.idPacienteFK());
        prontuario.setIdade(prontuarioDTO.idade());
        prontuario.setTipoSanguineo(prontuarioDTO.tipoSanguineo());
        prontuario.setAltura(prontuarioDTO.altura());
        prontuario.setPeso(prontuarioDTO.peso());
        prontuario.setTemperatura(prontuarioDTO.temperatura());
        prontuario.setPressao(prontuarioDTO.pressao());
        prontuario.setDescricao(prontuarioDTO.descricao());
        return prontuario;
    }
}
