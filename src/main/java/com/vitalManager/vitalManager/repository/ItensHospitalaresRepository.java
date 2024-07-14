package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.ItensHospitalaresModel;

import java.util.List;
import java.util.Optional;

public interface ItensHospitalaresRepository {
    ItensHospitalaresModel create(ItensHospitalaresModel item);
    Optional<ItensHospitalaresModel> findById(Integer id);
    List<ItensHospitalaresModel> findAll();
    ItensHospitalaresModel update(ItensHospitalaresModel item);
    void deleteById(Integer id);
}
