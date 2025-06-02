package com.example.appfutbol.scraper;

public class ScraperFactory {
    public static Scraper createScraper(String tipo) {
        switch (tipo.toLowerCase()) {
            case "posiciones":
                return new TabPosicionScraper();
            default:
                throw new IllegalArgumentException("Scraping no soportado");
        }
    }
}
