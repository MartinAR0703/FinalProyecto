/*module com.example.appfutbol {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.appfutbol to javafx.fxml;
    exports com.example.appfutbol;
}*/
module com.example.appfutbol {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires java.sql;
    requires java.desktop;
    requires java.compiler;
    requires org.jsoup;
    requires mysql.connector.j;


    opens com.example.appfutbol to javafx.fxml;
    exports com.example.appfutbol;
    opens com.example.appfutbol.Controllers to javafx.fxml;
    exports com.example.appfutbol.Controllers;
    opens com.example.appfutbol.models to javafx.base;
    exports com.example.appfutbol.models;
    opens com.example.appfutbol.Utils to javafx.base;
    exports com.example.appfutbol.Utils;
    opens com.example.appfutbol.scraper to javafx.base;
    exports com.example.appfutbol.scraper;
    exports com.example.appfutbol.Utils.BundesLiga;
    opens com.example.appfutbol.Utils.BundesLiga to javafx.base;
    exports com.example.appfutbol.Utils.LigaMX to javafx.base;
    exports com.example.appfutbol.Utils.SerieA;
    opens com.example.appfutbol.Utils.SerieA to javafx.base;
}