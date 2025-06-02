package com.example.appfutbol.models;

public class Equipo {
    private String nombre;
    private String ganados;
    private String empates;
    private String perdidos;
    private String puntos;

    public Equipo(String nombre, String ganados, String empates, String perdidos, String puntos) {
        this.nombre = nombre;
        this.ganados = ganados;
        this.empates = empates;
        this.perdidos = perdidos;
        this.puntos = puntos;
    }

    public String getNombre() { return nombre; }
    public String getGanados() { return ganados; }
    public String getEmpates() { return empates; }
    public String getPerdidos() { return perdidos; }
    public String getPuntos() { return puntos; }
}