package com.example.appfutbol.Utils.LigaMX;

import com.example.appfutbol.models.Equipo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.appfutbol.Utils.ScrapingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Estrategia para scrapear la tabla de posiciones de la Liga MX temporada 2024
 * desde la página oficial de ESPN.
 *
 * Implementa la interfaz {@link ScrapingStrategy} y devuelve una lista de objetos {@link Equipo}.
 */
public class EspnPosicionesMX2024Strategy implements ScrapingStrategy {

    /**
     * URL del sitio de ESPN donde se encuentra la tabla de posiciones de la Liga MX 2024.
     */
    private final String url = "https://www.espn.com.mx/futbol/posiciones/_/liga/MEX.1/temporada/2024";

    /**
     * Realiza el scraping de la tabla de posiciones.
     *
     * Extrae nombres de equipos, partidos ganados, empatados, perdidos y puntos,
     * y construye objetos {@link Equipo} para cada uno.
     *
     * @return Lista de equipos con sus respectivas estadísticas.
     */
    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos = new ArrayList<>();
        try {
            System.out.println("Scrapeando la tabla de posiciones de 2024...");

            // Obtiene el documento HTML de la página
            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            // Listas temporales para separar nombres de estadísticas
            List<String> nombresEquipos = new ArrayList<>();
            List<String[]> estadisticasEquipos = new ArrayList<>();

            // Recorre todas las filas de la tabla
            for (Element fila : filas) {
                Elements cols = fila.select("td");

                // Si la fila solo tiene una columna, se asume que es el nombre del equipo
                if (cols.size() == 1) {
                    String rawTexto = cols.get(0).text();

                    // Extrae el nombre usando una expresión regular para remover el prefijo de posición y código
                    Pattern pattern = Pattern.compile("^(\\d+)([A-Za-z]{3})(.*)$");
                    Matcher matcher = pattern.matcher(rawTexto);
                    String nombreEquipo;

                    if (matcher.find()) {
                        nombreEquipo = matcher.group(3).trim();
                    } else {
                        nombreEquipo = rawTexto;
                    }

                    nombresEquipos.add(nombreEquipo);

                    // Si tiene 8 o más columnas, se considera una fila de estadísticas
                } else if (cols.size() >= 8) {
                    String ganados   = cols.get(1).text();
                    String empates   = cols.get(2).text();
                    String perdidos  = cols.get(3).text();
                    String puntos    = cols.get(7).text();

                    estadisticasEquipos.add(new String[]{ganados, empates, perdidos, puntos});
                }
            }

            // Combina nombre + estadísticas para crear objetos Equipo
            for (int i = 0; i < Math.min(nombresEquipos.size(), estadisticasEquipos.size()); i++) {
                String nombre = nombresEquipos.get(i);
                String[] stats = estadisticasEquipos.get(i);
                equipos.add(new Equipo(nombre, stats[0], stats[1], stats[2], stats[3]));
            }

        } catch (Exception e) {
            System.out.println("Error al hacer scraping: " + e.getMessage());
        }

        return equipos;
    }
}
