package com.example.appfutbol.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    // Getters
    public String getNombre() { return nombre.get(); }
    public String getGoles() { return goles.get(); }
    public String getPartidos() { return partidos.get(); }
    public String getEquipo() { return equipo.get(); }

    // Setters
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setGoles(String goles) { this.goles.set(goles); }
    public void setPartidos(String partidos) { this.partidos.set(partidos); }
    public void setEquipo(String equipo) { this.equipo.set(equipo); }

    // Propiedades para binding (JavaFX)
    public StringProperty nombreProperty() { return nombre; }
    public StringProperty golesProperty() { return goles; }
    public StringProperty partidosProperty() { return partidos; }
    public StringProperty equipoProperty() { return equipo; }
}
