package com.example.appfutbol.Utils;

import java.util.List;

public interface ScrapingStrategy<T> {
    List<T> execute();
}
