package com.example.appfutbol.models;

/**
 * Representa un equipo de fútbol con estadísticas básicas como partidos ganados, empatados,
 * perdidos y puntos acumulados.
 *
 * @author MartinAR
 */
public class Equipo {
    private String nombre;
    private String ganados;
    private String empates;
    private String perdidos;
    private String puntos;

    /**
     * Constructor para inicializar un equipo con sus estadísticas.
     *
     * @param nombre Nombre del equipo.
     * @param ganados Número de partidos ganados.
     * @param empates Número de partidos empatados.
     * @param perdidos Número de partidos perdidos.
     * @param puntos Puntos acumulados por el equipo.
     */
    public Equipo(String nombre, String ganados, String empates, String perdidos, String puntos) {
        this.nombre = nombre;
        this.ganados = ganados;
        this.empates = empates;
        this.perdidos = perdidos;
        this.puntos = puntos;
    }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return Nombre del equipo.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el número de partidos ganados.
     *
     * @return Número de partidos ganados.
     */
    public String getGanados() { return ganados; }

    /**
     * Obtiene el número de partidos empatados.
     *
     * @return Número de partidos empatados.
     */
    public String getEmpates() { return empates; }

    /**
     * Obtiene el número de partidos perdidos.
     *
     * @return Número de partidos perdidos.
     */
    public String getPerdidos() { return perdidos; }

    /**
     * Obtiene los puntos acumulados por el equipo.
     *
     * @return Puntos acumulados.
     */
    public String getPuntos() { return puntos; }
}
