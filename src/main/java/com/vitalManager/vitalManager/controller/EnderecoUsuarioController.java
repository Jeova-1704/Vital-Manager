package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.service.EnderecoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnderecoUsuarioController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @GetMapping("/enderecos")
    public List<EnderecoUsuarioModel> getAllAdress() {
        return enderecoUsuarioService.getAllAdress();
    }

    @GetMapping("/endereco/{id}")
    public EnderecoUsuarioModel getAdressByUserId(@PathVariable int id) {
        return enderecoUsuarioService.getAdressByIdUser(id);
    }

    @PostMapping("/criarEndereco")
    public void createUser(@RequestBody EnderecoUsuarioDTO userDTO) {
        enderecoUsuarioService.createUsuarioAdress(userDTO);
    }

//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable int id, @RequestBody EnderecoUsuarioModel userDTO) {
//        enderecoUsuarioService.updateEnderecoUsuario(id, userDTO);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable int id) {
//        enderecoUsuarioService.deleteUserById(id);
//    }

}
