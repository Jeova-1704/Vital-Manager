package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.EmailNotFoundException;
import com.vitalManager.vitalManager.exception.EmailRegisteredSystemException;
import com.vitalManager.vitalManager.infra.security.TokenService;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import com.vitalManager.vitalManager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        UsuarioModel usuarioModel = this.repository.findByEmail(body.email()).orElseThrow(() -> new EmailNotFoundException("Email not found"));
        if (passwordEncoder.matches(body.senha(), usuarioModel.getSenha())) {
            String token = this.tokenService.generateToken(usuarioModel);
            return ResponseEntity.ok(new ResponseDTO(usuarioModel.getNome(), usuarioModel.getTipo(),token));
        }
        return  ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body) {
        Optional<UsuarioModel> usuarioModel = this.repository.findByEmail(body.email());
        if (usuarioModel.isEmpty()) {
            UsuarioModel user = usuarioService.convertDtoToModel(body);
            this.repository.save(user);
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), user.getTipo(),token));
        } else {
            throw new EmailRegisteredSystemException("O email já esta cadastrado no sistema.");
        }
    }
}