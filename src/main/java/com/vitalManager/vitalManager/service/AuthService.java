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
            return new ResponseDTO(usuarioModel.getNome(), usuarioModel.getTipo(), token);
        }
        throw new EmailNotFoundException("Invalid password");
    }

    public ResponseDTO register(UsuarioDTO body) {
        Optional<UsuarioModel> usuarioModel = repository.findByEmail(body.email());
        if (usuarioModel.isEmpty()) {
            UsuarioModel user = usuarioService.convertDtoToModel(body);
            repository.save(user);
            String token = tokenService.generateToken(user);
            return new ResponseDTO(user.getNome(), user.getTipo(), token);
        } else {
            throw new EmailRegisteredSystemException("O email já esta cadastrado no sistema.");
        }
    }
}
