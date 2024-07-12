package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EnderecoFornecedorDTO;
import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import com.vitalManager.vitalManager.service.EnderecoFornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecosFornecedor")
public class EnderecoFornecedorController {

    @Autowired
    private EnderecoFornecedorService enderecoFornecedorService;

    @GetMapping("/")
    public ResponseEntity<List<EnderecoFornecedorModel>> getAllAddresses() {
        List<EnderecoFornecedorModel> enderecoFornecedorModels = enderecoFornecedorService.getAllAddresses();
        return ResponseEntity.ok().body(enderecoFornecedorModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoFornecedorModel> updateSupplierAddress(@PathVariable int id, @RequestBody EnderecoFornecedorDTO enderecoFornecedorDTO) {
        EnderecoFornecedorModel enderecoFornecedorModel = enderecoFornecedorService.updateSupplierAddress(id, enderecoFornecedorDTO);
        return ResponseEntity.ok().body(enderecoFornecedorModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoFornecedorModel> getAddressBySupplierId(@PathVariable int id) {
        EnderecoFornecedorModel enderecoFornecedorModel = enderecoFornecedorService.getAddressBySupplierId(id);
        return ResponseEntity.ok().body(enderecoFornecedorModel);
    }

    @PostMapping("/")
    public ResponseEntity<EnderecoFornecedorModel> createEndereco(@RequestBody EnderecoFornecedorDTO enderecoFornecedorDTO) {
        EnderecoFornecedorModel enderecoFornecedorModel = enderecoFornecedorService.createSupplierAddress(enderecoFornecedorDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoFornecedorModel.getIdEnderecoFornecedor()).toUri();
        return ResponseEntity.created(uri).body(enderecoFornecedorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnderecoById(@PathVariable int id) {
        enderecoFornecedorService.deleteAddressBySupplierId(id);
        return ResponseEntity.noContent().build();
    }
}
