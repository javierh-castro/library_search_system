package com.literatura.challenge_literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}
