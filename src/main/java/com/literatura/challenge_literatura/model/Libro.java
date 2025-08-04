package com.literatura.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String languages;
    private String download_count;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Autor> authors;
}
