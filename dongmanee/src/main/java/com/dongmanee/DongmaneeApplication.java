package com.dongmanee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DongmaneeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DongmaneeApplication.class, args);
	}

}
