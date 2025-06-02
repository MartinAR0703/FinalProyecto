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

/**
 * Servicio para obtener los próximos partidos de la Liga MX utilizando
 * la API pública de ESPN.
 *
 * author MartinAR
 */
public class LigaMxService {

    /**
     * Realiza una solicitud HTTP a la API de ESPN para obtener el calendario de partidos de la Liga MX.
     *
     * @return Lista de objetos {@link partido} con el nombre de los equipos y la fecha del encuentro.
     * @throws Exception si ocurre un error al realizar la solicitud o procesar la respuesta.
     */
    public static List<partido> obtenerPartidos() throws Exception {
        // URL de la API de ESPN que contiene los próximos partidos de la Liga MX
        String url = "https://site.api.espn.com/apis/site/v2/sports/soccer/mex.1/scoreboard";

        // Crear cliente y solicitud HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        // Enviar solicitud y obtener respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parsear el cuerpo de la respuesta como JSON
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray eventos = json.getAsJsonArray("events");

        // Lista de partidos a retornar
        List<partido> partidos = new ArrayList<>();

        // Iterar sobre cada evento (partido) recibido
        for (int i = 0; i < eventos.size(); i++) {
            JsonObject evento = eventos.get(i).getAsJsonObject();
            JsonObject competencia = evento.getAsJsonArray("competitions").get(0).getAsJsonObject();
            JsonArray equipos = competencia.getAsJsonArray("competitors");

            // Extraer nombre de los equipos
            String equipo1 = equipos.get(0).getAsJsonObject().getAsJsonObject("team").get("displayName").getAsString();
            String equipo2 = equipos.get(1).getAsJsonObject().getAsJsonObject("team").get("displayName").getAsString();
            // Extraer fecha
            String fecha = competencia.get("date").getAsString();

            // Crear objeto partido y añadir a la lista
            partidos.add(new partido(equipo1, equipo2, fecha));
        }

        return partidos;
    }
}
