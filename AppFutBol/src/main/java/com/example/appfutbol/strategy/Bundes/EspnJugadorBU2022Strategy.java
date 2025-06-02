package com.example.appfutbol.strategy.Bundes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.appfutbol.scraper.Jugador;
import com.example.appfutbol.scraper.JugadorFactory;
import com.example.appfutbol.strategy.ScrapingStrategy;

import java.util.ArrayList;
import java.util.List;

public class EspnJugadorBU2022Strategy implements ScrapingStrategy {
    private final String url= "https://www.espn.com.mx/futbol/estadisticas/_/liga/GER.1/temporada/2022/vista/anotaciones/equipo";

    @Override
    public void execute() {
        try {
            System.out.println("Scrapeando mejores jugadores de BundesLiga 2022");
            Document doc = Jsoup.connect(url).get();
            Elements filas = doc.select("table tbody tr");

            List<Jugador> jugadores = new ArrayList<>();
            int contador = 0;

            for (Element fila : filas) {
                if (contador >=20) break;

                Elements columnas = fila.select("td");
                if (columnas.size() < 5) continue;

                String nombre = columnas.get(1).text();
                String equipo = columnas.get(2).text();
                String partidos = columnas.get(3).text();
                String goles = columnas.get(4).text();

                Jugador jugador = JugadorFactory.crearJugador(nombre, goles, partidos, equipo);
                jugadores.add(jugador);
                contador++;
            }
            jugadores.forEach(System.out::println);
        }catch (Exception e) {
            System.out.println("Error al scrapear jugadores: " + e.getMessage());
        }
    }
}
