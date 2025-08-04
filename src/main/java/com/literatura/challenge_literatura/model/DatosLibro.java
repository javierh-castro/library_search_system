package com.literatura.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> lenguaje,
        @JsonAlias("download_count") Integer descargas,
        @JsonAlias("authors") List<AutorDto> autores) {
}
