package com.example.appfutbol.Utils.SerieA;

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
 * Estrategia de scraping para obtener la tabla de posiciones de la Serie A (Italia) temporada 2023-2024
 * desde el sitio web de ESPN.
 * <p>
 * Implementa la interfaz {@link ScrapingStrategy} para extraer los datos de los equipos como nombre,
 * partidos ganados, empatados, perdidos y puntos.
 * </p>
 *
 * URL fuente: https://www.espn.com.mx/futbol/posiciones/_/liga/ITA.1/temporada/2023
 *
 * @author MartinAR
 */
public class EspnPosicionesSA2024Strategy implements ScrapingStrategy {

    /**
     * URL del sitio de ESPN donde se encuentra la tabla de posiciones de la Serie A 2023-2024.
     */
    private final String url = "https://www.espn.com.mx/futbol/posiciones/_/liga/ITA.1/temporada/2023";

    /**
     * Ejecuta el scraping de la tabla de posiciones y devuelve una lista de objetos {@link Equipo}
     * que representan los equipos de la Serie A con sus estadísticas clave.
     *
     * @return Lista de equipos con nombre, ganados, empatados, perdidos y puntos.
     */
    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos = new ArrayList<>();
        try {
            System.out.println("Scrapeando la tabla de posiciones de la Serie A 2023-2024...");

            // Conexión a la página y obtención del documento HTML
            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            // Listas auxiliares para separar nombres y estadísticas
            List<String> nombresEquipos = new ArrayList<>();
            List<String[]> estadisticasEquipos = new ArrayList<>();

            // Recorrido de todas las filas de la tabla
            for (Element fila : filas) {
                Elements cols = fila.select("td");

                // Fila con el nombre del equipo (generalmente una sola columna)
                if (cols.size() == 1) {
                    String rawTexto = cols.get(0).text();
                    Pattern pattern = Pattern.compile("^(\\d+)([A-Za-z]{3})(.*)$");
                    Matcher matcher = pattern.matcher(rawTexto);
                    String nombreEquipo;
                    if (matcher.find()) {
                        nombreEquipo = matcher.group(3).trim();
                    } else {
                        nombreEquipo = rawTexto;
                    }
                    nombresEquipos.add(nombreEquipo);

                    // Fila con estadísticas (mínimo 8 columnas)
                } else if (cols.size() >= 8) {
                    String ganados = cols.get(1).text();
                    String empates = cols.get(2).text();
                    String perdidos = cols.get(3).text();
                    String puntos = cols.get(7).text();

                    estadisticasEquipos.add(new String[]{ganados, empates, perdidos, puntos});
                }
            }

            // Unión de nombres y estadísticas en objetos Equipo
            for (int i = 0; i < Math.min(nombresEquipos.size(), estadisticasEquipos.size()); i++) {
                String nombre = nombresEquipos.get(i);
                String[] stats = estadisticasEquipos.get(i);
                equipos.add(new Equipo(nombre, stats[0], stats[1], stats[2], stats[3]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipos;
    }
}
