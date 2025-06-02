package com.example.appfutbol.scraper;


public class JugadorFactory {
    public static Jugador crearJugador(String nombre, String goles, String partidos, String equipo ) {
        return new Jugador(nombre, goles, partidos, equipo);
    }
}
