package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.EnderecoUsuarioDocsController;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.service.EnderecoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecos")
public class EnderecoUsuarioController implements EnderecoUsuarioDocsController {

    private final EnderecoUsuarioService enderecoUsuarioService;

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
    @PostMapping("/criarEndereco")
    public ResponseEntity<EnderecoUsuarioModel> createUser(@RequestBody EnderecoUsuarioDTO userDTO) {
        EnderecoUsuarioModel enderecoUsuariomodel = enderecoUsuarioService.createUsuarioAdress(userDTO);
        return ResponseEntity.ok().body(enderecoUsuariomodel);
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
