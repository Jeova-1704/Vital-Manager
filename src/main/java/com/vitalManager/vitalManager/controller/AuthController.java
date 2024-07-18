package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTOLogin;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.exception.EmailNotFoundException;
import com.vitalManager.vitalManager.exception.EmailRegisteredSystemException;
import com.vitalManager.vitalManager.infra.security.TokenService;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import com.vitalManager.vitalManager.service.AuthService;
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
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        try {
            ResponseDTOLogin response = service.login(body);
            return ResponseEntity.ok(response);
        } catch (EmailNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body) {
        try {
            ResponseDTO response = service.register(body);
            return ResponseEntity.ok(response);
        } catch (EmailRegisteredSystemException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}