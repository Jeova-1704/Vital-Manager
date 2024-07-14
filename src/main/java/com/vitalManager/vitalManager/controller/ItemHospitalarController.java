package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ItemHospitalarDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import com.vitalManager.vitalManager.service.ItemHospitalarService;
import com.vitalManager.vitalManager.controller.encapsulationDocumentation.ItemHospitalarDocsController;
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
@RequestMapping("/item-hospitalar")
public class ItemHospitalarController implements ItemHospitalarDocsController {

    private final ItemHospitalarService itemHospitalarService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<ItensHospitalaresModel>> getAllItensHospitalares() {
        List<ItensHospitalaresModel> listaItemHospitalar = itemHospitalarService.getAllItemHospitalar();
        return ResponseEntity.ok().body(listaItemHospitalar);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ItensHospitalaresModel> getItemHospitalarById(@PathVariable int id) {
        ItensHospitalaresModel itemHospitalar = itemHospitalarService.getItemHospitalarById(id);
        if (itemHospitalar != null) {
            return ResponseEntity.ok().body(itemHospitalar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<ItensHospitalaresModel> createItemHospitalar(@RequestBody ItemHospitalarDTO itemHospitalarDTO) {
        ItensHospitalaresModel itemHospitalar = itemHospitalarService.createItemHospitalar(itemHospitalarDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemHospitalar.getIdItensHospitalares()).toUri();
        return ResponseEntity.created(uri).body(itemHospitalar);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ItensHospitalaresModel> updateItemHospitalar(@PathVariable int id, @Valid @RequestBody ItemHospitalarDTO itemHospitalarDTO) {
        try {
            ItensHospitalaresModel itemHospitalar = itemHospitalarService.updateItemHospitalar(id, itemHospitalarDTO);
            if (itemHospitalar != null) {
                return ResponseEntity.ok().body(itemHospitalar);
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
    public ResponseEntity<Void> deleteItemHospitalarById(@PathVariable int id) {
        boolean deleted = itemHospitalarService.deleteItemHospitar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
