package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.EnderecoUsuarioDocsController;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.service.EnderecoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecos")
public class EnderecoUsuarioController implements EnderecoUsuarioDocsController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @GetMapping("/")
    public ResponseEntity<List<EnderecoUsuarioModel>> getAllAdress() {
        List<EnderecoUsuarioModel> enderecoUsuarioModels = enderecoUsuarioService.getAllAdress();
        return ResponseEntity.ok().body(enderecoUsuarioModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoUsuarioModel> updateUserAdress(@PathVariable int id,@RequestBody EnderecoUsuarioDTO enderecoUsuarioDTO) {
        EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioService.updateUserAdress(id,enderecoUsuarioDTO);
        return ResponseEntity.ok().body(enderecoUsuarioModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoUsuarioModel> getAdressByUserId(@PathVariable int id) {
        EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioService.getAdressByIdUser(id);
        return ResponseEntity.ok().body(enderecoUsuarioModel);
    }

    @PostMapping("/")
    public ResponseEntity<EnderecoUsuarioModel> createEndereco(@RequestBody EnderecoUsuarioDTO adressDTO) {
        EnderecoUsuarioModel enderecoUsuarioModel = enderecoUsuarioService.createUsuarioAdress(adressDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoUsuarioModel.getIdEnderecoUsuario()).toUri();
        return ResponseEntity.created(uri).body(enderecoUsuarioModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndrecoById(@PathVariable int id) {
        enderecoUsuarioService.deleteAdressByUserId(id);
        return ResponseEntity.noContent().build();
    }

}
