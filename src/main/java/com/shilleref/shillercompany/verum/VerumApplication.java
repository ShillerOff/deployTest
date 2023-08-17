package com.shilleref.shillercompany.verum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VerumApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerumApplication.class, args);
	}

}
