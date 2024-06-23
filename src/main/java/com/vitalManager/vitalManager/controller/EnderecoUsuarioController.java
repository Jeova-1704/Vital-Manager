package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.EnderecoUsuarioDocsController;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.service.EnderecoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoUsuarioController implements EnderecoUsuarioDocsController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<EnderecoUsuarioModel>> getAllAdress() {
        List<EnderecoUsuarioModel> enderecoUsuarioModels = enderecoUsuarioService.getAllAdress();
        return ResponseEntity.ok().body(enderecoUsuarioModels);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoUsuarioModel> getAdressByUserId(@PathVariable int id) {
        EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioService.getAdressByIdUser(id);
        return ResponseEntity.ok().body(enderecoUsuarioModel);
    }

    @Override
    @PostMapping("/criar")
    public ResponseEntity<EnderecoUsuarioModel> createUser(@RequestBody EnderecoUsuarioDTO adressDTO) {
        EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioService.createUsuarioAdress(adressDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoUsuarioModel.getIdEnderecoUsuario()).toUri();
        return ResponseEntity.created(uri).body(enderecoUsuarioModel);
    }

//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable int id, @RequestBody EnderecoUsuarioModel userDTO) {
//        enderecoUsuarioService.updateEnderecoUsuario(id, userDTO);
//    }
//
//          Implemente o ResponseEntity por favor
//
//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable int id) {
//        enderecoUsuarioService.deleteUserById(id);
//    }

}
