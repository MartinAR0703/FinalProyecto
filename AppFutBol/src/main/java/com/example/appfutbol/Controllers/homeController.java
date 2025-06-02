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

/**
 * Controlador de la pantalla principal (Home) de la aplicación de fútbol.
 *
 * Permite al usuario navegar entre distintas vistas correspondientes a ligas de fútbol
 * y cerrar la aplicación.
 *
 * @author MartinAR
 */

public class homeController {
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnLigaMx;
    @FXML
    private Button btnSerieA;
    @FXML
    private Button btnBundesLiga;


    /**
     * Cierra la aplicación cuando se hace clic en el botón "Cerrar sesión".
     *
     * @param actionEvent el evento de acción generado por el botón
     * @throws IOException si ocurre un error de entrada/salida (aunque no se usa aquí)
     */

    public void onBtnCerrarClick(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }
    /**
     * Cambia la escena actual a la vista de la Liga MX.
     *
     * @param actionEvent el evento de acción generado por el botón
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public void onBtnLigaMxClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/ligaMx.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // Obtiene el stage actual
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * Cambia la escena actual a la vista de la Serie A.
     *
     * @param actionEvent el evento de acción generado por el botón
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public void onBtnSerieAClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/serieA.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Cambia la escena actual a la vista de la Bundesliga.
     *
     * @param actionEvent el evento de acción generado por el botón
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public void onBtnBundesLigaClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/bundesliga.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}