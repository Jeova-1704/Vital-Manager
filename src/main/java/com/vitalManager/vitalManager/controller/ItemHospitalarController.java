package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.ItemHospitalarDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import com.vitalManager.vitalManager.service.ItemHospitalarService;
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
public class ItemHospitalarController {

    private final ItemHospitalarService itemHospitalarService;

    @GetMapping("/")
    public ResponseEntity<List<ItensHospitalaresModel>> getAllProntuario() {
        List<ItensHospitalaresModel> LitaItemHospitalar = itemHospitalarService.getAllItemHospitalar();
        return ResponseEntity.ok().body(LitaItemHospitalar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensHospitalaresModel> getProntuarioById(@PathVariable int id) {
        ItensHospitalaresModel itemHospitalar = itemHospitalarService.getItemHospitalarById(id);
        if (itemHospitalar != null) {
            return ResponseEntity.ok().body(itemHospitalar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ItensHospitalaresModel> createProntuario(@RequestBody ItemHospitalarDTO itemHospitalarDTO) {
        ItensHospitalaresModel itemHospitalar = itemHospitalarService.createItemHospitalar(itemHospitalarDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemHospitalar.getIdItensHospitalares()).toUri();
        return ResponseEntity.created(uri).body(itemHospitalar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensHospitalaresModel> updateProntuario(@PathVariable int id, @Valid @RequestBody ItemHospitalarDTO itemHospitalarDTO) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuarioById(@PathVariable int id) {
        boolean deleted = itemHospitalarService.deleteItemHospitar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
