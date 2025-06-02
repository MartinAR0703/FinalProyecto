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

public class EspnPosicionesBU2024Strategy implements ScrapingStrategy {
    private final String url= "https://www.espn.com.mx/futbol/posiciones/_/liga/GER.1/temporada/2024"; //Bundesliga 2024-2025

    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos = new ArrayList<>();
        try {
            System.out.println("Scrapeando la tabla de posiciones de la Serie A 2023-2024...");

            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            List<String> nombresEquipos = new ArrayList<>();
            List<String[]> estadisticasEquipos = new ArrayList<>();

            for (Element fila : filas) {
                Elements cols = fila.select("td");

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

                } else if (cols.size() >= 8) {
                    String ganados  = cols.get(1).text();
                    String empates  = cols.get(2).text();
                    String perdidos = cols.get(3).text();
                    String puntos   = cols.get(7).text();

                    estadisticasEquipos.add(new String[]{ganados, empates, perdidos, puntos});
                }
            }

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