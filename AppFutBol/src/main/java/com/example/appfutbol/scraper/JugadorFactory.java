package com.example.appfutbol.scraper;

import com.example.appfutbol.models.Jugador;

/**
 * Factory para crear objetos Jugador.
 *
 * Proporciona un método estático para instanciar jugadores con sus atributos.
 *
 * @author MartinAR
 */
public class JugadorFactory {

    /**
     * Crea un nuevo objeto Jugador con los datos proporcionados.
     *
     * @param nombre Nombre del jugador.
     * @param goles Cantidad de goles del jugador, en formato String.
     * @param partidos Número de partidos jugados, en formato String.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @return Nueva instancia de Jugador.
     */
    public static Jugador crearJugador(String nombre, String goles, String partidos, String equipo) {
        return new Jugador(nombre, goles, partidos, equipo);
    }
}
