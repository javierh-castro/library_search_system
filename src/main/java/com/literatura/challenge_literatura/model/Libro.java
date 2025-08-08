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
    private String titulo;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;
    private Integer numeroDescargas;
    //    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autor;

    // Constructor vacío requerido por JPA
    public Libro() {
    }

    // Constructor desde DatosLibro
//    public Libro(DatosLibro datos) {
//        this.titulo = datos.titulo();
//        this.download_count = String.valueOf(datos.numeroDescargas());
//
//        if (datos.lenguaje() != null && !datos.lenguaje().isEmpty()) {
//            this.languages = String.join(", ", datos.lenguaje());
//        }
//
//        if (datos.autor() != null) {
//            this.authors = datos.autor().stream()
//                    .map(datoAutor -> {
//                        Autor autor = new Autor();
//                        autor.setNombre(datoAutor.nombre());
//                        autor.setLibro(this); // clave para mantener la relación
//                        return autor;
//                    })
//                    .toList();
//        }
//    }
    public Libro(DatosLibro datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.numeroDescargas = datosLibros.numeroDescargas();

    }

    public void setAutor(List<Autor> autores) {
        this.autor = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Autor> getAutor() {
        return autor;
    }
}
