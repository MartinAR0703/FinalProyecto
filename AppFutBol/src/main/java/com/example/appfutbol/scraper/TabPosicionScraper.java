package com.example.appfutbol.scraper;

import com.example.appfutbol.strategy.ScrapingStrategy;

public class TabPosicionScraper implements Scraper {
    private ScrapingStrategy strategy;

    @Override
    public void setStrategy(ScrapingStrategy strategy) {
        this.strategy=strategy;
    }

    @Override
    public void scrape() {
        if (strategy !=null) {
            strategy.execute();
        } else {
            System.out.println("No se ha definido una estrategia de scraping.");
        }
    }
}
