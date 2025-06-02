package com.example.appfutbol.models;

import javafx.beans.property.SimpleStringProperty;

public class Jugador {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty goles;
    private final SimpleStringProperty partidos;
    private final SimpleStringProperty equipo;

    public Jugador(String nombre, String goles, String partidos, String equipo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.goles = new SimpleStringProperty(goles);
        this.partidos = new SimpleStringProperty(partidos);
        this.equipo = new SimpleStringProperty(equipo);
    }

    public String getNombre() { return nombre.get(); }
    public String getGoles() { return goles.get(); }
    public String getPartidos() { return partidos.get(); }
    public String getEquipo() { return equipo.get(); }
}
