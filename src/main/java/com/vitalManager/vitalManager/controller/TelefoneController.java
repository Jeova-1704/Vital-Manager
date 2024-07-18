package com.vitalManager.vitalManager.controller;


import com.vitalManager.vitalManager.DTO.TelefoneDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.TelefoneDocsController;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telefones")
public class TelefoneController implements TelefoneDocsController {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping("/")
    public ResponseEntity<List<TelefoneModel>> getAllPhones() {
        List<TelefoneModel> enderecoUsuarioModels = telefoneService.getAllPhones();
        return ResponseEntity.ok().body(enderecoUsuarioModels);
    }

    @Override
    public ResponseEntity<TelefoneModel> getPhoneByUserId(int id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneModel> updateUserPhone(@PathVariable int id,@RequestBody TelefoneDTO enderecoUsuarioDTO) {
        TelefoneModel telefoneModel =  telefoneService.updatePhone(id,enderecoUsuarioDTO);
        return ResponseEntity.ok().body(telefoneModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneModel> getPhoneById(@PathVariable int id) {
        TelefoneModel telefoneModel = telefoneService.getPhoneById(id).orElseThrow();
        return ResponseEntity.ok().body(telefoneModel);
    }

    @PostMapping("/")
    public ResponseEntity<TelefoneModel> createPhone(@RequestBody TelefoneDTO telefoneDTO) {
        TelefoneModel telefoneModel = telefoneService.createPhone(telefoneDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(telefoneModel.getIdTelefone()).toUri();
        return ResponseEntity.created(uri).body(telefoneModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneById(@PathVariable int id) {
        telefoneService.deletePhoneByUserId(id);
        return ResponseEntity.noContent().build();
    }

}
