package com.example.appfutbol.scraper;

import com.example.appfutbol.Utils.ScrapingStrategy;

public interface Scraper {
        void setStrategy(ScrapingStrategy strategy);
        void scrape();

}
