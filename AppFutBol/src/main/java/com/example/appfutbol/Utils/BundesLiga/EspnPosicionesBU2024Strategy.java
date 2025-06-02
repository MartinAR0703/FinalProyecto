package com.example.appfutbol.Utils.BundesLiga;

import com.example.appfutbol.Utils.ScrapingStrategy;
import com.example.appfutbol.models.Equipo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Estrategia de scraping para obtener la tabla de posiciones
 * de la Bundesliga 2024 desde la página de ESPN México.
 *
 * Implementa la interfaz ScrapingStrategy para retornar una lista de equipos con sus estadísticas.
 */
public class EspnPosicionesBU2024Strategy implements ScrapingStrategy {

    /**
     * URL de la página de ESPN que contiene la tabla de posiciones
     * de la Bundesliga para la temporada 2024.
     */
    private final String url= "https://www.espn.com.mx/futbol/posiciones/_/liga/GER.1/temporada/2024";

    /**
     * Realiza el scraping de la tabla de posiciones y devuelve una lista
     * de objetos Equipo con nombre, ganados, empates, perdidos y puntos.
     *
     * @return Lista de equipos con sus estadísticas de la temporada actual.
     */
    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos = new ArrayList<>();
        try {
            System.out.println("Scrapeando la tabla de posiciones de la Serie A 2023-2024...");

            // Conecta y obtiene el documento HTML de la URL
            Document doc = Jsoup.connect(url).get();

            // Selecciona las filas de la tabla que contienen los datos
            Elements filas = doc.select("table tbody tr");

            // Listas auxiliares para almacenar nombres y estadísticas separadamente
            List<String> nombresEquipos = new ArrayList<>();
            List<String[]> estadisticasEquipos = new ArrayList<>();

            // Recorre cada fila para extraer datos
            for (Element fila : filas) {
                Elements cols = fila.select("td");

                if (cols.size() == 1) {
                    // Caso donde la fila contiene sólo el nombre del equipo, con posibles números y letras al inicio
                    String rawTexto = cols.get(0).text();
                    Pattern pattern = Pattern.compile("^(\\d+)([A-Za-z]{3})(.*)$");
                    Matcher matcher = pattern.matcher(rawTexto);
                    String nombreEquipo;
                    if (matcher.find()) {
                        nombreEquipo = matcher.group(3).trim(); // Extrae sólo el nombre sin números ni letras
                    } else {
                        nombreEquipo = rawTexto;
                    }
                    nombresEquipos.add(nombreEquipo);

                } else if (cols.size() >= 8) {
                    // Extrae las estadísticas: ganados, empates, perdidos y puntos
                    String ganados  = cols.get(1).text();
                    String empates  = cols.get(2).text();
                    String perdidos = cols.get(3).text();
                    String puntos   = cols.get(7).text();

                    estadisticasEquipos.add(new String[]{ganados, empates, perdidos, puntos});
                }
            }

            // Combina nombres y estadísticas para crear objetos Equipo
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
