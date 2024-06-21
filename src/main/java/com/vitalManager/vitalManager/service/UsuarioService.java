package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.Enum.SexoEnum;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel getUserById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void createUser(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(usuarioDTO.nome());
        usuario.setSobrenome(usuarioDTO.sobrenome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setDataNascimento(usuarioDTO.dataNascimento());
        usuario.setSexo(SexoEnum.fromDescricao(String.valueOf(usuarioDTO.sexo())));
        usuario.setDate(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    public void updateUser(int id, UsuarioModel usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setSobrenome(usuarioDTO.getSobrenome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setSenha(usuarioDTO.getSenha());
        usuarioExistente.setDataNascimento(usuarioDTO.getDataNascimento());
        usuarioExistente.setSexo(SexoEnum.fromDescricao(String.valueOf(usuarioDTO.getSexo())));
        usuarioExistente.setDate(LocalDateTime.now());

        usuarioRepository.update(usuarioExistente);
    }

    public void deleteUserById(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        usuarioRepository.deleteById(id);
    }


}
