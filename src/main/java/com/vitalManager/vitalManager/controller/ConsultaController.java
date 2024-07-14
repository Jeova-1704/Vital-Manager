package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ConsultaDTO;
import com.vitalManager.vitalManager.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/{id}")
    public ConsultaDTO getConsultaById(@PathVariable int id) {
        return consultaService.findById(id);
    }

    @GetMapping
    public List<ConsultaDTO> getAllConsultas() {
        return consultaService.findAll();
    }

    @PostMapping
    public void createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        consultaService.save(consultaDTO);
    }

    @PutMapping("/{id}")
    public void updateConsulta(@PathVariable int id, @RequestBody ConsultaDTO consultaDTO) {
        consultaDTO = new ConsultaDTO(
                id,
                consultaDTO.idProntuario(),
                consultaDTO.idMedico(),
                consultaDTO.data(),
                consultaDTO.valor(),
                consultaDTO.hora(),
                consultaDTO.status(),
                consultaDTO.observacoes()
        );
        consultaService.update(consultaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable int id) {
        consultaService.delete(id);
    }
}
