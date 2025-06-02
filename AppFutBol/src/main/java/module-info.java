module com.example.appfutbol {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.appfutbol to javafx.fxml;
    exports com.example.appfutbol;
}