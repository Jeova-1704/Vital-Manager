package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ConsultaDTO;
import com.vitalManager.vitalManager.service.ConsultaService;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.ConsultaDocsController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController implements ConsultaDocsController {
    private final ConsultaService consultaService;

    @Override
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        List<ConsultaDTO> consultas = consultaService.findAll();
        return ResponseEntity.ok(consultas);
    }

    @Override
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable int id) {
        ConsultaDTO consulta = consultaService.findById(id);
        return ResponseEntity.ok(consulta);
    }

    @Override
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        consultaService.save(consultaDTO);
        return ResponseEntity.status(201).body(consultaDTO);
    }

    @Override
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable int id, @RequestBody ConsultaDTO consultaDTO) {
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
        return ResponseEntity.ok(consultaDTO);
    }

    @Override
    public ResponseEntity<Void> deleteConsultaById(@PathVariable int id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
