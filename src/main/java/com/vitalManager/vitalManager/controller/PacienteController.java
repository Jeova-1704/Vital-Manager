package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.PacienteDTO;
import com.vitalManager.vitalManager.model.PacienteModel;
import com.vitalManager.vitalManager.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/")
    public ResponseEntity<List<PacienteModel>> getAllPacientes() {
        List<PacienteModel> listaPacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok().body(listaPacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> getPacienteById(@PathVariable int id) {
        PacienteModel paciente = pacienteService.getPacienteById(id);
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping("/")
    public ResponseEntity<PacienteModel> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteModel paciente = pacienteService.createPaciente(pacienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();
        System.out.println(paciente.getIdPaciente());
        return ResponseEntity.created(uri).body(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteModel> updatePaciente(@PathVariable int id, @RequestBody PacienteModel pacienteDTO) {
        PacienteModel paciente = pacienteService.updatePaciente(id, pacienteDTO);
        return ResponseEntity.ok().body(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable int id) {
        pacienteService.deletePacienteById(id);
        return ResponseEntity.noContent().build();
    }
}
