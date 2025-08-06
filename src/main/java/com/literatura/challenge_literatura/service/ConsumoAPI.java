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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();
//        String jsonn = response.body().string();
        System.out.println("Respuesta cruda de la API: " + json.strip());
        if (json == null || json.isBlank()) {
            throw new RuntimeException("La respuesta de la API está vacía");
        }
        return json;
    }
}
