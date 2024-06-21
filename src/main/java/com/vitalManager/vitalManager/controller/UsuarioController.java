package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioModel>> getAllUsers() {
        List<UsuarioModel> listaUsuario = usuarioService.getAllUsuarios();
        return ResponseEntity.ok().body(listaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable int id) {
        UsuarioModel user = usuarioService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioModel> createUser(@RequestBody UsuarioDTO userDTO) {
        UsuarioModel user = usuarioService.createUsuario(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> updateUser(@PathVariable int id, @RequestBody UsuarioModel userDTO) {
        UsuarioModel usuario = usuarioService.updateUser(id, userDTO);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        usuarioService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
