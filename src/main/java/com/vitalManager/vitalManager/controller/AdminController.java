package com.vitalManager.vitalManager.controller;

import com.vitalManager.vitalManager.DTO.AdminDTO;
import com.vitalManager.vitalManager.model.AdminModel;
import com.vitalManager.vitalManager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public ResponseEntity<List<AdminModel>> getAllAdmins() {
        List<AdminModel> listaAdmins = adminService.getAllAdmins();
        return ResponseEntity.ok().body(listaAdmins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminModel> getAdminById(@PathVariable int id) {
        AdminModel admin = adminService.getAdminById(id);
        return ResponseEntity.ok().body(admin);
    }

    @PostMapping("/")
    public ResponseEntity<AdminModel> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminModel admin = adminService.createAdmin(adminDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(admin.getIdAdmin()).toUri();
        return ResponseEntity.created(uri).body(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminModel> updateAdmin(@PathVariable int id, @RequestBody AdminModel adminDTO) {
        AdminModel admin = adminService.updateAdmin(id, adminDTO);
        return ResponseEntity.ok().body(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable int id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.noContent().build();
    }
}
