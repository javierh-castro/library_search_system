package com.literatura.challenge_literatura.repository;

import com.literatura.challenge_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreTitulo);
}
