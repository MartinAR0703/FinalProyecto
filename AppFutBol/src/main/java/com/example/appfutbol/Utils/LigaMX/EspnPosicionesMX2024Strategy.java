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

public class EspnPosicionesMX2024Strategy implements ScrapingStrategy {
    private final String url= "https://www.espn.com.mx/futbol/posiciones/_/liga/MEX.1/temporada/2024"; //ligaMX 2024-2025

    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos= new ArrayList<>();
        try {
            System.out.println("Scrapeando La tabla de posiciones de 2024...");

            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            //Listas para almacenar el nombre por un lado y la estadistica por otro
            List<String> nombresEquipos = new ArrayList<>();
            List<String[]> estadisticasEquipos = new ArrayList<>();

            // Recorrer cada fila sin filtrar
            for (Element fila : filas) {
                Elements cols = fila.select("td");
                // Esta fila corresponde al nombre
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
                    // Esta fila corresponde a las estadísticas
                } else if (cols.size() >= 8) {
                    String ganados   = cols.get(1).text();
                    String empates   = cols.get(2).text();
                    String perdidos  = cols.get(3).text();
                    String puntos    = cols.get(7).text();

                    // Guardamos los datos en un arreglo
                    estadisticasEquipos.add(new String[]{ganados, empates, perdidos, puntos});
                }
            }

            // toma el nombre y la estadistica y crea un objeto de tipo equipo y lo añade a la lista equipos.
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