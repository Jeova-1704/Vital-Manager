package com.vitalManager.vitalManager.config;

import com.vitalManager.vitalManager.DTO.FornecedorDTO;
import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import com.vitalManager.vitalManager.model.FornecedorModel;
import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import com.vitalManager.vitalManager.repository.FornecedorRepository;

import com.vitalManager.vitalManager.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DataInitializer {

    @Autowired
    private FornecedorService fornecedorService;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            FornecedorDTO fornecedor1 = new FornecedorDTO(null, "Fornecedor 1", "12345678000101");
            FornecedorDTO fornecedor2 = new FornecedorDTO(null, "Fornecedor 2", "12345678000102");
            FornecedorDTO fornecedor3 = new FornecedorDTO(null, "Fornecedor 3", "12345678000103");
            fornecedorService.createFornecedor(fornecedor1);
            fornecedorService.createFornecedor(fornecedor2);
            fornecedorService.createFornecedor(fornecedor3);

        };
    }
}
