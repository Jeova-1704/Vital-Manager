package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.AdminModel;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    List<AdminModel> findAll();
    Optional<AdminModel> findById(int id);
    int save(AdminModel admin);
    int update(AdminModel admin);
    int deleteById(int id);
    boolean existsById(int id);
}
