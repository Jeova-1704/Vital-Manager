package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.RegisterRequestDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.infra.security.TokenService;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
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
public class AuthController {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO body) {
        UsuarioModel usuarioModel = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(usuarioModel.getSenha(), body.password())) {
            String token = this.tokenService.generateToken(usuarioModel);
            return ResponseEntity.ok(new ResponseDTO(usuarioModel.getNome(), token));
        }

        return  ResponseEntity.badRequest().build();

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO body) {
        Optional<UsuarioModel> usuarioModel = this.repository.findByEmail(body.email());
        System.out.println("*********************** MODEL ******************");
        System.out.println(usuarioModel);
        if (usuarioModel.isEmpty()) {
            UsuarioModel user = new UsuarioModel();
            System.out.println("*********************** AQUI ******************");
            user.setNome(body.nome());
            System.out.println(body.nome());
            user.setSobrenome(body.sobrenome());
            System.out.println(body.sobrenome());
            user.setEmail(body.email());
            System.out.println(body.email());
            user.setSenha(passwordEncoder.encode(body.senha()));
            System.out.println(body.senha());
            user.setDataNascimento(body.dataNascimento());
            System.out.println(body.dataNascimento());
            user.setSexo(body.sexo());
            System.out.println(body.sexo());
            user.setTipo(body.tipo());
            System.out.println(body.tipo());
            user.setDate(LocalDateTime.now());

            this.repository.save(user);

            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
        }
        return  ResponseEntity.badRequest().build();

    }
}
