package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.EstoqueDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.EstoqueDocsController;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.EstoqueModel;
import com.vitalManager.vitalManager.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estoque")
public class EstoqueController implements EstoqueDocsController {

    private final EstoqueService EstoqueService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<EstoqueModel>> getAllEstoquees() {
        List<EstoqueModel> listaEstoquees = EstoqueService.getAllEstoques();
        return ResponseEntity.ok().body(listaEstoquees);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueModel> getEstoqueById(@PathVariable int id) {
        EstoqueModel estoque = EstoqueService.getEstoqueById(id);
        return ResponseEntity.ok().body(estoque);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<EstoqueModel> createEstoque(@RequestBody EstoqueDTO EstoqueDTO) {
        EstoqueModel estoque = EstoqueService.createEstoque(EstoqueDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estoque.getIdEstoque()).toUri();
        return ResponseEntity.created(uri).body(estoque);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueModel> updateEstoque(@PathVariable int id, @RequestBody EstoqueDTO estoqueDTO) {
        EstoqueModel estoque = EstoqueService.updateEstoque(id, estoqueDTO);
        return ResponseEntity.ok().body(estoque);

    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoqueById(@PathVariable int id) {
        EstoqueService.deleteEstoqueById(id);
        return ResponseEntity.noContent().build();
    }
}