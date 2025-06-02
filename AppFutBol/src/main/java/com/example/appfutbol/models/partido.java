package com.example.appfutbol.models;

public class partido {
    private String equipoLocal;
    private String equipoVisitante;
    private String fecha;

    public partido(String equipoLocal, String equipoVisitante, String fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
    }

    public String getEquipoLocal() { return equipoLocal; }
    public String getEquipoVisitante() { return equipoVisitante; }
    public String getFecha() { return fecha; }
}
