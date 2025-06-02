package com.example.appfutbol.Utils.BundesLiga;

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

public class EspnPosicionesBU2022Strategy implements ScrapingStrategy {
    private final String url= "https://www.espn.com.mx/futbol/posiciones/_/liga/GER.1/temporada/2022"; //Bundesliga 2022-2023

    @Override
    public List<Equipo> execute() {
        List<Equipo> equipos= new ArrayList<>();
        try {
            System.out.println("Scrapeando La tabla de posiciones de BundesLiga 2022-2023...");

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

            // Combinamos la información
            if (nombresEquipos.size() == estadisticasEquipos.size()) {
                for (int i = 0; i < nombresEquipos.size(); i++) {
                    String nombre = nombresEquipos.get(i);
                    String[] stats = estadisticasEquipos.get(i);
                    System.out.println(nombre + " - " + stats[0] +
                            " - " + stats[1] +
                            " - " + stats[2] +
                            " - " + stats[3]);
                }
            } else {
                // imprimir ambos tamaños para depurar
                System.out.println("El número de nombres y filas de datos no coincide.");
                System.out.println("Nombres encontrados: " + nombresEquipos.size());
                System.out.println("Filas de estadísticas encontradas: " + estadisticasEquipos.size());
            }
        } catch (Exception e) {
            System.out.println("Error al hacer scraping: " + e.getMessage());
        }
        return equipos;
    }
}