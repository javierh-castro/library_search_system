package com.literatura.challenge_literatura;

import com.literatura.challenge_literatura.Principal.Principal;
import com.literatura.challenge_literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola putas");
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
