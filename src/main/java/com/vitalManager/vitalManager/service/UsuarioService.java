package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.EmailRegisteredSystemException;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.infra.security.TokenService;
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
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UsuarioModel findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }


    public UsuarioModel registerUsuario(UsuarioDTO usuario) {
        UsuarioModel verificao = findByEmail(usuario.email());
        if (verificao == null) {
            UsuarioModel user = new UsuarioModel();
            user.setNome(usuario.nome());
            user.setSobrenome(usuario.sobrenome());
            user.setEmail(usuario.email());
            user.setSenha(passwordEncoder.encode(usuario.senha()));
            user.setDataNascimento(usuario.dataNascimento());
            user.setSexo(usuario.sexo());
            user.setTipo(usuario.tipo());
            user.setDate(LocalDateTime.now());
            usuarioRepository.save(user);
            return user;
        } else {
            throw new EmailRegisteredSystemException("O email já está registrado no sistema");
        }


    }

    public UsuarioModel createUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(usuarioDTO.nome());
        usuario.setSobrenome(usuarioDTO.sobrenome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setDataNascimento(usuarioDTO.dataNascimento());
        usuario.setSexo(usuarioDTO.sexo());
        usuario.setTipo(usuarioDTO.tipo());
        usuario.setDate(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public UsuarioModel updateUser(int id, UsuarioModel usuarioDTO) {
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
        usuarioExistente.setSexo(usuarioDTO.getSexo());
        usuarioExistente.setTipo(usuarioDTO.getTipo());
        usuarioExistente.setDate(LocalDateTime.now());
        usuarioRepository.update(usuarioExistente);
        return usuarioExistente;
    }

    public void deleteUserById(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        usuarioRepository.deleteById(id);
    }


}
