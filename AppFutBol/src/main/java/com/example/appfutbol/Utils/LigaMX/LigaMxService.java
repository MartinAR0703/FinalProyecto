package com.example.appfutbol.Utils.LigaMX;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.example.appfutbol.models.partido;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class LigaMxService {
    public static List<partido> obtenerPartidos() throws Exception {
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/mex.1/scoreboard";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray eventos = json.getAsJsonArray("events");

        List<partido> partidos = new ArrayList<>();

        for (int i = 0; i < eventos.size(); i++) {
            JsonObject evento = eventos.get(i).getAsJsonObject();
            JsonObject competencia = evento.getAsJsonArray("competitions").get(0).getAsJsonObject();
            JsonArray equipos = competencia.getAsJsonArray("competitors");

            String equipo1 = equipos.get(0).getAsJsonObject().getAsJsonObject("team").get("displayName").getAsString();
            String equipo2 = equipos.get(1).getAsJsonObject().getAsJsonObject("team").get("displayName").getAsString();
            String fecha = competencia.get("date").getAsString();

            partidos.add(new partido(equipo1, equipo2, fecha));
        }

        return partidos;
    }
}
