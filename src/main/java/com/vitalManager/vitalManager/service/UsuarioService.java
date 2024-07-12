package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel getUserById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public UsuarioModel createUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = convertDtoToModel(usuarioDTO);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public UsuarioModel updateUser(int id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UsuarioModel usuarioAtualizar = convertDtoToModel(usuarioDTO);
        usuarioAtualizar.setIdUsuario(usuarioExistente.getIdUsuario());
        usuarioRepository.update(usuarioAtualizar);
        return usuarioAtualizar;
    }

    public void deleteUserById(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel convertDtoToModel(UsuarioDTO body) {
        UsuarioModel user = new UsuarioModel();
        user.setNome(body.nome());
        user.setSobrenome(body.sobrenome());
        user.setCpf(body.CPF());
        user.setEmail(body.email());
        user.setSenha(passwordEncoder.encode(body.senha()));
        user.setDataNascimento(body.dataNascimento());
        user.setSexo(body.sexo());
        user.setTipo(body.tipo());
        user.setDataCriacao(LocalDateTime.now());
        return user;
    }


}