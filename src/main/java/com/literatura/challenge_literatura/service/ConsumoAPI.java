package com.literatura.challenge_literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url){
        System.out.println("la url: " + url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException  e) {
            System.out.println("Error al enviar solicitud HTTP: " + e.getMessage());
            e.printStackTrace(); // <-- Agregá esto para ver más detalles
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();
        System.out.println("Respuesta cruda de la API: " + json.strip());
        return json;
    }
}
