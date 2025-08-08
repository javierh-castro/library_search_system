package com.literatura.challenge_literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        if (json == null || json.isBlank()) {
            throw new RuntimeException("La respuesta JSON está vacía.");
        }

        try {
            return objectMapper.readValue(json,clase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
