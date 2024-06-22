package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.AuthDocsController;
import com.vitalManager.vitalManager.exception.EmailNotFoundException;
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

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocsController {
    private final UsuarioService services;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        UsuarioModel usuarioModel = services.findByEmail(body.email());
        if (passwordEncoder.matches(body.senha(), usuarioModel.getSenha())) {
            String token = this.tokenService.generateToken(usuarioModel);
            return ResponseEntity.ok(new ResponseDTO(usuarioModel.getNome(), token));
        }
        return  ResponseEntity.badRequest().build();
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body) {
        UsuarioModel user = services.registerUsuario(body);
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
    }
}
