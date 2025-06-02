package com.example.appfutbol.scraper;

/**
 * Fábrica para crear instancias de Scraper según el tipo solicitado.
 * Soporta distintos tipos de scraping, como posiciones, jugadores, etc.
 *
 * @author MartinAR
 */
public class ScraperFactory {

    /**
     * Crea un Scraper concreto según el tipo indicado.
     *
     * @param tipo Tipo de scraper a crear (por ejemplo: "posiciones").
     * @return Instancia concreta de Scraper.
     * @throws IllegalArgumentException Si el tipo no es soportado.
     */
    public static Scraper createScraper(String tipo) {
        switch (tipo.toLowerCase()) {
            case "posiciones":
                return new TabPosicionScraper();
            default:
                throw new IllegalArgumentException("Scraping no soportado: " + tipo);
        }
    }
}
