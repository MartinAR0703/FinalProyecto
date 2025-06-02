package com.example.appfutbol.models;

/**
 * Representa un partido de fútbol entre dos equipos y la fecha en que se juega.
 *
 * @author MartinAR
 */
public class partido {
    private String equipoLocal;
    private String equipoVisitante;
    private String fecha;

    /**
     * Constructor para crear un partido con equipos y fecha.
     *
     * @param equipoLocal Nombre del equipo local.
     * @param equipoVisitante Nombre del equipo visitante.
     * @param fecha Fecha en la que se juega el partido.
     */
    public partido(String equipoLocal, String equipoVisitante, String fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
    }

    /**
     * Obtiene el nombre del equipo local.
     *
     * @return Nombre del equipo local.
     */
    public String getEquipoLocal() { return equipoLocal; }

    /**
     * Obtiene el nombre del equipo visitante.
     *
     * @return Nombre del equipo visitante.
     */
    public String getEquipoVisitante() { return equipoVisitante; }

    /**
     * Obtiene la fecha del partido.
     *
     * @return Fecha del partido.
     */
    public String getFecha() { return fecha; }
}

