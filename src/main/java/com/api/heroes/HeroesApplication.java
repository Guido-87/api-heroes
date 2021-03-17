package com.api.heroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HeroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesApplication.class, args);
	}
}
