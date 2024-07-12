package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ExameDTO;
import com.vitalManager.vitalManager.model.ExameModel;
import com.vitalManager.vitalManager.service.ExameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exames")
@RequiredArgsConstructor
public class ExameController {

    private final ExameService exameService;

    @GetMapping
    public ResponseEntity<List<ExameModel>> getAllExames() {
        List<ExameModel> exames = exameService.getAllExames();
        return ResponseEntity.ok(exames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExameModel> getExameById(@PathVariable int id) {
        ExameModel exame = exameService.getExameById(id);
        return ResponseEntity.ok(exame);
    }

    @PostMapping
    public ResponseEntity<ExameModel> createExame(@RequestBody ExameDTO exameDTO) {
        ExameModel exame = exameService.createExame(exameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(exame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExameModel> updateExame(@PathVariable int id, @RequestBody ExameDTO exameDTO) {
        ExameModel exame = exameService.updateExame(id, exameDTO);
        return ResponseEntity.ok(exame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExameById(@PathVariable int id) {
        exameService.deleteExameById(id);
        return ResponseEntity.noContent().build();
    }
}
