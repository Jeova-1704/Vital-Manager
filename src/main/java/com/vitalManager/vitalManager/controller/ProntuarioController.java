package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ProntuarioDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.ProntuarioDocsController;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ProntuarioModel;
import com.vitalManager.vitalManager.service.ProntuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prontuarios")
public class ProntuarioController implements ProntuarioDocsController {

    private final ProntuarioService prontuarioService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<ProntuarioModel>> getAllProntuario() {
        List<ProntuarioModel> listaProntuarios = prontuarioService.getAllProntuario();
        return ResponseEntity.ok().body(listaProntuarios);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioModel> getProntuarioById(@PathVariable int id) {
        ProntuarioModel prontuario = prontuarioService.getProntuarioById(id);
        if (prontuario != null) {
            return ResponseEntity.ok().body(prontuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<ProntuarioModel> createProntuario(@RequestBody ProntuarioDTO prontuarioDTO) {
        ProntuarioModel prontuario = prontuarioService.createProntuario(prontuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prontuario.getIdPacienteFK()).toUri();
        return ResponseEntity.created(uri).body(prontuario);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioModel> updateProntuario(@PathVariable int id, @Valid @RequestBody ProntuarioDTO prontuarioDTO) {
        try {
            ProntuarioModel prontuario = prontuarioService.updateProntuario(id, prontuarioDTO);
            if (prontuario != null) {
                return ResponseEntity.ok().body(prontuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuarioById(@PathVariable int id) {
        boolean deleted = prontuarioService.deleteProntuario(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}