package com.vitalManager.vitalManager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Vital Manager", version = "1.0", description = "Sistema de gestão hospitalar API"))
public class VitalManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalManagerApplication.class, args);
	}

}
