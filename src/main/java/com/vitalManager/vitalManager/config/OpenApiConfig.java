package com.vitalManager.vitalManager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI custonOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema de gerenciamento hospitalar e clinica ")
                        .version("v1")
                        .description("API Desenvolvida para a diciplina de banco de dados")
                        .termsOfService("Termos de serviços URL")
                );
    }
}
