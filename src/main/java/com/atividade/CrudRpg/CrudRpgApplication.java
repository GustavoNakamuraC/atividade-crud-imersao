package com.atividade.CrudRpg;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Crud Rpg API",
				version = "1",
				description = "Um trabalho para desenvolver um api com o tema RPG, valendo nota para a matéria de Imersão."))
public class CrudRpgApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRpgApplication.class, args);
	}

}
