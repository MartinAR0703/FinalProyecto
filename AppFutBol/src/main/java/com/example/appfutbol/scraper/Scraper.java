package com.example.appfutbol.scraper;

import com.example.appfutbol.strategy.ScrapingStrategy;

public interface Scraper {
        void setStrategy(ScrapingStrategy strategy);
        void scrape();

}
