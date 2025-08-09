package com.literatura.challenge_literatura.repository;

import com.literatura.challenge_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreTitulo);
    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i = :idioma")
    List<Libro> findByContainingIdioma(@Param("idioma") String idioma);
}
