package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.FornecedorDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.FornecedorDocsController;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.FornecedorModel;
import com.vitalManager.vitalManager.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fornecedor")
public class FornecedorController implements FornecedorDocsController {

    private final FornecedorService fornecedorService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<FornecedorModel>> getAllFornecedores() {
        List<FornecedorModel> listaFornecedores = fornecedorService.getAllFornecedores();
        return ResponseEntity.ok().body(listaFornecedores);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorModel> getFornecedorById(@PathVariable int id) {
        FornecedorModel fornecedor = fornecedorService.getFornecedorById(id);
        return ResponseEntity.ok().body(fornecedor);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<FornecedorModel> createFornecedor(@RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorModel fornecedor = fornecedorService.createFornecedor(fornecedorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getIdFornecedor()).toUri();
        return ResponseEntity.created(uri).body(fornecedor);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorModel> updateFornecedor(@PathVariable int id, @RequestBody FornecedorDTO fornecedorDTO) {
        try {
            FornecedorModel fornecedor = fornecedorService.updateFornecedor(id, fornecedorDTO);
            return ResponseEntity.ok().body(fornecedor);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedorById(@PathVariable int id) {
        fornecedorService.deleteFornecedorById(id);
        return ResponseEntity.noContent().build();
    }
}
