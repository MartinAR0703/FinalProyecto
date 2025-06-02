package com.example.appfutbol.scraper;


import com.example.appfutbol.models.Jugador;

public class JugadorFactory {
    public static Jugador crearJugador(String nombre, String goles, String partidos, String equipo ) {
        return new Jugador(nombre, goles, partidos, equipo);
    }
}
