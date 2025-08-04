package com.literatura.challenge_literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDto(
        String name,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento) {
}
