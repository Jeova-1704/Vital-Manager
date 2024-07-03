package com.vitalManager.vitalManager.service;

import com.vitalManager.vitalManager.DTO.AdminDTO;
import com.vitalManager.vitalManager.exception.ResourceNotFoundException;
import com.vitalManager.vitalManager.model.AdminModel;
import com.vitalManager.vitalManager.model.UsuarioModel;
import com.vitalManager.vitalManager.repository.AdminRepository;
import com.vitalManager.vitalManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AdminModel> getAllAdmins() {
        return adminRepository.findAll();
    }

    public AdminModel getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + id));
    }

    public AdminModel createAdmin(AdminDTO adminDTO) {
        UsuarioModel usuario = usuarioRepository.findById(adminDTO.idUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + adminDTO.idUsuario()));

        if (usuario.getTipo().equals("A") || usuario.getTipo().equals("Admin")) {

            AdminModel admin = new AdminModel();
            admin.setUsuario(usuario);
            admin.setPermissao(adminDTO.permissao());
            adminRepository.save(admin);
            return admin;

        } else  {
            throw new RuntimeException("Usuário não é do tipo Admin");
        }
    }

    public AdminModel updateAdmin(int id, AdminModel adminDTO) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin not found with id " + id);
        }
        AdminModel adminExistente = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + id));

        adminExistente.setPermissao(adminDTO.getPermissao());
        adminRepository.update(adminExistente);
        return adminExistente;
    }

    public void deleteAdminById(int id) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin not found with id " + id);
        }
        adminRepository.deleteById(id);
    }
}
