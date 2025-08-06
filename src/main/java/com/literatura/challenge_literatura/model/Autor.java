package com.literatura.challenge_literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer nacimiento;
    private Integer fallecimiento;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public Autor() {}

    // Getters y setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    // Otros getters y setters si necesitas nacimiento/fallecimiento
}