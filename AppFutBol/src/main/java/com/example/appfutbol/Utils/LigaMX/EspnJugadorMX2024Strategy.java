package com.example.appfutbol.Utils.LigaMX;

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
 * Estrategia de scraping para obtener los mejores jugadores de la Liga MX 2024
 * desde la página de ESPN México.
 *
 * Implementa la interfaz ScrapingStrategy para devolver una lista de objetos Jugador.
 */
public class EspnJugadorMX2024Strategy implements ScrapingStrategy {

    /**
     * URL de la página de ESPN que contiene las estadísticas de anotaciones de jugadores
     * de la Liga MX en la temporada 2024.
     */
    private final String url= "https://www.espn.com.mx/futbol/estadisticas/_/liga/MEX.1/temporada/2024/vista/anotaciones/equipo";

    /**
     * Realiza el scraping para obtener la lista de los 20 mejores jugadores
     * en cuanto a anotaciones, incluyendo nombre, equipo, partidos jugados y goles.
     *
     * @return Lista de jugadores con sus datos extraídos de la web.
     */
    @Override
    public List<Jugador> execute() {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            System.out.println("Scrapeando mejores jugadores de Liga MX 2024");

            // Conexión y obtención del documento HTML
            Document doc = Jsoup.connect(url).get();

            // Selección de las filas de la tabla con los datos de jugadores
            Elements filas = doc.select("table tbody tr");

            int contador = 0;

            // Itera sobre las filas, limita a los primeros 20 jugadores
            for (Element fila : filas) {
                if (contador >= 20) break;

                Elements columnas = fila.select("td");
                if (columnas.size() < 5) continue; // Asegura que la fila tenga suficientes columnas

                String nombre = columnas.get(1).text();
                String equipo = columnas.get(2).text();
                String partidos = columnas.get(3).text();
                String goles = columnas.get(4).text();

                // Crea un objeto Jugador usando la fábrica
                Jugador jugador = JugadorFactory.crearJugador(nombre, goles, partidos, equipo);
                jugadores.add(jugador);
                contador++;
            }

            // Imprime la lista de jugadores (para depuración)
            jugadores.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error al scrapear jugadores: " + e.getMessage());
        }
        return jugadores;
    }
}
