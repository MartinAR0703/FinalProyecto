package com.example.appfutbol.scraper;

import com.example.appfutbol.Utils.ScrapingStrategy;

/**
 * Implementación de Scraper para obtener las posiciones de equipos usando una estrategia de scraping.
 *
 * Esta clase permite definir y ejecutar una estrategia concreta para scrapear datos de posiciones.
 *
 * @author MartinAR
 */
public class TabPosicionScraper implements Scraper {
    private ScrapingStrategy strategy;

    /**
     * Establece la estrategia concreta de scraping que se usará.
     *
     * @param strategy Estrategia de scraping a utilizar.
     */
    @Override
    public void setStrategy(ScrapingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Ejecuta la estrategia de scraping definida.
     * Si no hay estrategia definida, imprime un mensaje de advertencia.
     */
    @Override
    public void scrape() {
        if (strategy != null) {
            strategy.execute();
        } else {
            System.out.println("No se ha definido una estrategia de scraping.");
        }
    }
}
