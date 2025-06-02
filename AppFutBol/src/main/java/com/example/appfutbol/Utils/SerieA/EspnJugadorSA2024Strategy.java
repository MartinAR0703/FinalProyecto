package com.example.appfutbol.Utils.SerieA;

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
 * Estrategia de scraping para obtener los mejores jugadores (goleadores) de la Serie A 2023-2024
 * desde el sitio web de ESPN.
 * <p>
 * Esta clase implementa la interfaz {@link ScrapingStrategy} y utiliza JSoup para realizar la extracción
 * de datos HTML. Extrae información como el nombre del jugador, su equipo, partidos jugados y goles anotados.
 * </p>
 *
 * @author MartinAR
 */
public class EspnJugadorSA2024Strategy implements ScrapingStrategy {

    /**
     * URL del sitio de ESPN que contiene la tabla de goleadores de la Serie A para la temporada 2023-2024.
     */
    private final String url = "https://www.espn.com.mx/futbol/posiciones/_/liga/ITA.1/temporada/2023";

    /**
     * Ejecuta el proceso de scraping de los jugadores y devuelve una lista de los 20 mejores goleadores.
     *
     * @return Lista de objetos {@link Jugador} con nombre, goles, partidos y equipo.
     */
    @Override
    public List<Jugador> execute() {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            System.out.println("Scrapeando mejores jugadores de Serie A 2023-2024");

            // Conectarse a la URL y obtener el documento HTML
            Document doc = Jsoup.connect(url).get();

            // Seleccionar todas las filas de la tabla
            Elements filas = doc.select("table tbody tr");

            int contador = 0;

            // Recorrer las filas y extraer datos de hasta 20 jugadores
            for (Element fila : filas) {
                if (contador >= 20) break;

                Elements columnas = fila.select("td");
                if (columnas.size() < 5) continue;

                String nombre = columnas.get(1).text();
                String equipo = columnas.get(2).text();
                String partidos = columnas.get(3).text();
                String goles = columnas.get(4).text();

                // Crear objeto Jugador utilizando la factoría
                Jugador jugador = JugadorFactory.crearJugador(nombre, goles, partidos, equipo);
                jugadores.add(jugador);
                contador++;
            }

            // Imprimir resultados (opcional)
            jugadores.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error al scrapear jugadores: " + e.getMessage());
        }

        return jugadores;
    }
}
