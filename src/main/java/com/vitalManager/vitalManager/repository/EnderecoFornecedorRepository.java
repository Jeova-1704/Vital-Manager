package com.vitalManager.vitalManager.repository;

import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;

import java.util.List;
import java.util.Optional;

public interface EnderecoFornecedorRepository {
    List<EnderecoFornecedorModel> findAll();
    EnderecoFornecedorModel findBySupplierId(int id);

    int save(EnderecoFornecedorModel enderecoFornecedorModel);
    int update(EnderecoFornecedorModel enderecoFornecedorModel);
    int deleteBySupplierId(int id);
    boolean existsBySupplierId(int id);
}