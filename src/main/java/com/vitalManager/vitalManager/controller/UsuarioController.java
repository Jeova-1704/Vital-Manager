package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<UsuarioModel> getAllUsers() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioModel getUserById(@PathVariable int id) {
        return usuarioService.getUserById(id);
    }

    @PostMapping("/")
    public void createUser(@RequestBody UsuarioDTO userDTO) {
        usuarioService.createUsuario(userDTO);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UsuarioModel userDTO) {
        usuarioService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        usuarioService.deleteUserById(id);
    }
}
