package com.example.appfutbol.scraper;

public class Jugador {
    private String nombre;
    private String equipo;
    private String goles;
    private String partidos;

    public Jugador(String nombre, String goles, String partidos, String equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.goles = goles;
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return nombre +" | Goles: " + goles + " | PJ: " + partidos + " | Equipo: " + equipo;
    }
    public String getNombre() { return nombre;}
    public String getEquipo() { return equipo;}
    public String getGoles() { return goles;}
    public String getPartidos() { return partidos;}
}
