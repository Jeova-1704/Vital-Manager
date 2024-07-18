package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.PrescricaoDTO;
import com.vitalManager.vitalManager.model.PrescricaoModel;
import com.vitalManager.vitalManager.service.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    private final PrescricaoService prescricaoService;

    @Autowired
    public PrescricaoController(PrescricaoService prescricaoService) {
        this.prescricaoService = prescricaoService;
    }

    @GetMapping
    public ResponseEntity<List<PrescricaoModel>> getAllPrescricoes() {
        List<PrescricaoModel> prescricoes = prescricaoService.getAllPrescricoes();
        return ResponseEntity.ok(prescricoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescricaoModel> getPrescricaoById(@PathVariable("id") int id) {
        PrescricaoModel prescricao = prescricaoService.getPrescricaoById(id);
        return ResponseEntity.ok(prescricao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PrescricaoModel> createPrescricao(@RequestBody PrescricaoDTO prescricaoDTO) {
        PrescricaoModel createdPrescricao = prescricaoService.createPrescricao(prescricaoDTO);
        return new ResponseEntity<>(createdPrescricao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescricaoModel> updatePrescricao(@PathVariable("id") int id, @RequestBody PrescricaoDTO prescricaoDTO) {
        PrescricaoModel updatedPrescricao = prescricaoService.updatePrescricao(id, prescricaoDTO);
        return ResponseEntity.ok(updatedPrescricao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePrescricao(@PathVariable("id") int id) {
        prescricaoService.deletePrescricaoById(id);
        return ResponseEntity.noContent().build();
    }
}
