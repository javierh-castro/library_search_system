package com.literatura.challenge_literatura.repository;

import com.literatura.challenge_literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutoRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    Optional<Autor> findByNombreIgnoreCase(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte > :anio)")
    List<Autor> buscarAutoresVivosEnAnio(int anio);

}
