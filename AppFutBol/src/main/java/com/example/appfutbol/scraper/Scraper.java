package com.example.appfutbol.scraper;

import com.example.appfutbol.Utils.ScrapingStrategy;

/**
 * Interfaz que define el comportamiento básico de un scraper.
 * Permite asignar una estrategia de scraping y ejecutar el proceso de extracción de datos.
 *
 * @author MartinAR
 */
public interface Scraper {

        /**
         * Establece la estrategia de scraping a utilizar.
         *
         * @param strategy Estrategia de scraping que implementa ScrapingStrategy.
         */
        void setStrategy(ScrapingStrategy strategy);

        /**
         * Ejecuta el proceso de scraping según la estrategia asignada.
         */
        void scrape();
}
