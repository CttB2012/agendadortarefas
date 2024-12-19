package com.projeto.agendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendadortarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadortarefasApplication.class, args);
	}

}
