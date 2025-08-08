package com.literatura.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    @ManyToMany(mappedBy = "autor")
    private List<Libro> libros;

    public Autor() {}

    public Autor(AutorDto d) {
        this.nombre = d.nombre();
        this.fechaDeNacimiento = d.fechaDeNacimiento();
        this.fechaDeMuerte = d.fechaDeMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return fechaDeNacimiento;
    }

    public void setNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFallecimiento() {
        return fechaDeMuerte;
    }

    public void setFallecimiento(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}