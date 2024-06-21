package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.MedicoDTO;
import com.vitalManager.vitalManager.model.MedicoModel;
import com.vitalManager.vitalManager.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/")
    public List<MedicoModel> getAllMedicos() {
        return medicoService.getAllMedicos();
    }

    @GetMapping("/{id}")
    public MedicoModel getMedicoById(@PathVariable int id) {
        return medicoService.getMedicoById(id);
    }

    @PostMapping("/")
    public void createMedico(@RequestBody MedicoDTO medicoDTO) {
        medicoService.createMedico(medicoDTO);
    }

    @PutMapping("/{id}")
    public void updateMedico(@PathVariable int id, @RequestBody MedicoModel medicoDTO) {
        medicoService.updateMedico(id, medicoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicoById(@PathVariable int id) {
        medicoService.deleteMedicoById(id);
    }
}
