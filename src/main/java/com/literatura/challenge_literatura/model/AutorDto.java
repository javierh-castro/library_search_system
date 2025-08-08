package com.literatura.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDto(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year")Integer fechaDeNacimiento,
        @JsonAlias("death_year")Integer fechaDeMuerte) {
}
