package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.TelefoneDTO;
import com.vitalManager.vitalManager.DTO.TelefoneFornecedorDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.EnderecoUsuarioDocsController;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.TelefoneDocsController;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.TelefoneFornecedorDocsController;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import com.vitalManager.vitalManager.service.EnderecoUsuarioService;
import com.vitalManager.vitalManager.service.TelefoneFornecedorService;
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
@RequestMapping("/telefonesFornecedor")
public class TelefoneFornecedorController implements TelefoneFornecedorDocsController {

    @Autowired
    private TelefoneFornecedorService telefoneFornecedorService;

    @GetMapping("/")
    public ResponseEntity<List<TelefoneFornecedorModel>> getAllPhones() {
        List<TelefoneFornecedorModel> enderecoUsuarioModels = telefoneFornecedorService.getAllPhones();
        return ResponseEntity.ok().body(enderecoUsuarioModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneFornecedorModel> updateUserPhone(@PathVariable int id,@RequestBody TelefoneFornecedorDTO telefoneFornecedorDTO) {
        TelefoneFornecedorModel telefoneFornecedorModel =  telefoneFornecedorService.updatePhone(id,telefoneFornecedorDTO);
        return ResponseEntity.ok().body(telefoneFornecedorModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneFornecedorModel> getPhoneByUserId(@PathVariable int id) {
        TelefoneFornecedorModel telefoneFornecedorModel = telefoneFornecedorService.getPhoneByIdUser(id);
        return ResponseEntity.ok().body(telefoneFornecedorModel);
    }

    @PostMapping("/")
    public ResponseEntity<TelefoneFornecedorModel> createPhone(@RequestBody TelefoneFornecedorDTO telefoneFornecedorDTO) {
        TelefoneFornecedorModel telefoneFornecedorModel = telefoneFornecedorService.createPhone(telefoneFornecedorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(telefoneFornecedorModel.getTelefone()).toUri();
        return ResponseEntity.created(uri).body(telefoneFornecedorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneById(@PathVariable int id) {
        telefoneFornecedorService.deletePhoneByUserId(id);
        return ResponseEntity.noContent().build();
    }

}
