package com.example.appfutbol.Utils.BundesLiga;

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
 * Servicio para obtener los partidos de la Bundesliga mediante la API de football-data.org.
 *
 * @author MartinAR
 */
public class BundesligaService {

    private static final String API_KEY = "02da04d0eb99411c9e723271cef049e5";

    /**
     * Obtiene la lista de partidos de la Bundesliga para una temporada específica.
     *
     * @param temporada Año de la temporada para obtener los partidos.
     * @return Lista de objetos partido con información de equipos y fecha.
     * @throws Exception si ocurre un error en la conexión o en el parseo de la respuesta.
     */
    public static List<partido> obtenerPartidosBundesliga(int temporada) throws Exception {
        String url = "https://api.football-data.org/v4/competitions/BL1/matches?season=" + temporada;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("X-Auth-Token", API_KEY)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray partidosJson = json.getAsJsonArray("matches");

        List<partido> partidos = new ArrayList<>();

        for (int i = 0; i < partidosJson.size(); i++) {
            JsonObject partidoObj = partidosJson.get(i).getAsJsonObject();

            String equipoLocal = partidoObj.getAsJsonObject("homeTeam").get("name").getAsString();
            String equipoVisitante = partidoObj.getAsJsonObject("awayTeam").get("name").getAsString();
            String fecha = partidoObj.get("utcDate").getAsString();

            partidos.add(new partido(equipoLocal, equipoVisitante, fecha));
        }

        return partidos;
    }
}
