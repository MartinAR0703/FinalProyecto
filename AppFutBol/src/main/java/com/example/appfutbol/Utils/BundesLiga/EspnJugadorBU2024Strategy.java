package com.example.appfutbol.Utils.BundesLiga;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.appfutbol.models.Jugador;
import com.example.appfutbol.scraper.JugadorFactory;
import com.example.appfutbol.Utils.ScrapingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Estrategia de scraping para obtener los mejores jugadores de la Bundesliga temporada 2024-2025 desde ESPN.
 * <p>
 * Utiliza Jsoup para parsear la página y extraer los datos principales de los jugadores (nombre, equipo, partidos y goles).
 * </p>
 *
 * @author MartinAR
 */
public class EspnJugadorBU2024Strategy implements ScrapingStrategy {
    private final String url = "https://www.espn.com.mx/futbol/estadisticas/_/liga/GER.1/temporada/2024/vista/anotaciones/equipo";

    /**
     * Ejecuta el scraping para obtener una lista de los mejores jugadores, limitando a los primeros 20.
     *
     * @return Lista de objetos Jugador con nombre, goles, partidos y equipo.
     */
    @Override
    public List<Jugador> execute() {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            System.out.println("Scrapeando mejores jugadores de la Bundesliga 2024-2025...");
            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            int contador = 0;
            for (Element fila : filas) {
                if (contador >= 20) break;  // Limitar a 20 jugadores

                Elements columnas = fila.select("td");
                if (columnas.size() < 5) continue;  // Validar estructura esperada

                String nombre = columnas.get(1).text();
                String equipo = columnas.get(2).text();
                String partidos = columnas.get(3).text();
                String goles = columnas.get(4).text();

                Jugador jugador = JugadorFactory.crearJugador(nombre, goles, partidos, equipo);
                jugadores.add(jugador);
                contador++;
            }

        } catch (Exception e) {
            System.out.println("Error al scrapear jugadores: " + e.getMessage());
        }

        return jugadores;
    }
}
