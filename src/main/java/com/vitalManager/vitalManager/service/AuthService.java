package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.EmailNotFoundException;
import com.vitalManager.vitalManager.exception.EmailRegisteredSystemException;
import com.vitalManager.vitalManager.infra.security.TokenService;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseDTO login(LoginDTO body) {
        UsuarioModel usuarioModel = repository.findByEmail(body.email())
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));
        if (passwordEncoder.matches(body.senha(), usuarioModel.getSenha())) {
            String token = tokenService.generateToken(usuarioModel);
            return new ResponseDTO(usuarioModel.getNome(), usuarioModel.getTipo(), usuarioModel.getIdUsuario(), token);
        }
        throw new EmailNotFoundException("Invalid password");
    }

    public ResponseDTO register(UsuarioDTO body) {
        Optional<UsuarioModel> usuarioModel = repository.findByEmail(body.email());
        if (usuarioModel.isEmpty()) {
            UsuarioModel usuario = usuarioService.convertDtoToModel(body);
            repository.save(usuario);
            usuario = repository.findByEmail(body.email())
                    .orElseThrow(() -> new EmailNotFoundException("Email not found"));
            String token = tokenService.generateToken(usuario);
            return new ResponseDTO(usuario.getNome(), usuario.getTipo(), usuario.getIdUsuario(),  token);
        } else {
            throw new EmailRegisteredSystemException("O email já esta cadastrado no sistema.");
        }
    }
}
