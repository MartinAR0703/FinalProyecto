package com.example.appfutbol.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnLigaMx;
    @FXML
    private Button btnSerieA;
    @FXML
    private Button btnBundesLiga;

    public void onBtnCerrarClick(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
    public void onBtnLigaMxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/ligaMx.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // Obtiene el stage actual
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void onBtnSerieAClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/serieA.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // Obtiene el stage actual
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void onBtnBundesLigaClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/bundesliga.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // Obtiene el stage actual
        stage.setScene(new Scene(root));
        stage.show();

    }

}
