package com.example.ProgettoLibreria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProgettoLibreriaApplication implements CommandLineRunner {
	@Autowired
	ILibroRep iLibroRep;

	@Autowired
	IUtenteRep iUtenteRep;

	public static void main(String[] args) {
		SpringApplication.run(ProgettoLibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Libro libro = new Libro("10 Piccoli Indiani", "Agatha Christie", "storia di 10 piccoli indiani");
		iLibroRep.save(libro);

		Utente utente = new Utente("prova", "prova", "prova", "provaprova");
		
		UtenteLibro utenteLibro = new UtenteLibro();
		utenteLibro.setUtente(utente);
		utenteLibro.setLibro(libro);


		Set<UtenteLibro> utenteLibri = new HashSet<>();
		utenteLibri.add(utenteLibro);
		utente.setUtente_libro(utenteLibri);

		iUtenteRep.save(utente);
	}


}
