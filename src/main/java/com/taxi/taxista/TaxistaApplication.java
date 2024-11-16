package com.taxi.taxista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.taxi.taxista.repository")

public class TaxistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxistaApplication.class, args);
	}

}
