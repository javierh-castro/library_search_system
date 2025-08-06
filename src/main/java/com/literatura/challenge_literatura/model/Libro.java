package com.literatura.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String languages;
    private String download_count;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
    private List<Autor> authors;

    // Constructor vacío requerido por JPA
    public Libro() {}

    // Constructor desde DatosLibro
    public Libro(DatosLibro datos) {
        this.title = datos.titulo();
        this.download_count = String.valueOf(datos.numeroDescargas());

        if (datos.lenguaje() != null && !datos.lenguaje().isEmpty()) {
            this.languages = String.join(", ", datos.lenguaje());
        }

        if (datos.autor() != null) {
            this.authors = datos.autor().stream()
                    .map(datoAutor -> {
                        Autor autor = new Autor();
//                        autor.setNombre(datoAutor.name());
                        autor.setNombre(datoAutor.nombre());
                        autor.setLibro(this); // clave para mantener la relación
                        return autor;
                    })
                    .toList();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getDownload_count() {
        return download_count;
    }

    public void setDownload_count(String download_count) {
        this.download_count = download_count;
    }

    public List<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }
}
