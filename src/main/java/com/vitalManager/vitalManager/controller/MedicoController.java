package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.MedicoDTO;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.MedicoDocsController;
import com.vitalManager.vitalManager.model.MedicoModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController implements MedicoDocsController {

    @Autowired
    private MedicoService medicoService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<MedicoModel>> getAllMedicos() {
        List<MedicoModel> listaMedicos = medicoService.getAllMedicos();
        return ResponseEntity.ok().body(listaMedicos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MedicoModel> getMedicoById(@PathVariable int id) {
        MedicoModel medico = medicoService.getMedicoById(id);
        return ResponseEntity.ok().body(medico);
    }

    @Override
    @PostMapping("/")
    public  ResponseEntity<MedicoModel> createMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoModel medico = medicoService.createMedico(medicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getIdMedico()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MedicoModel> updateMedico(@PathVariable int id, @RequestBody MedicoModel medicoDTO) {
        MedicoModel medico = medicoService.updateMedico(id, medicoDTO);
        return ResponseEntity.ok().body(medico);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicoById(@PathVariable int id) {
        medicoService.deleteMedicoById(id);
        return ResponseEntity.noContent().build();
    }
}
