package com.example.appfutbol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX para DeportivApp.
 * <p>
 * Esta clase carga la interfaz gráfica definida en el archivo FXML
 * {@code FlipLogin.fxml} y muestra la ventana principal.
 * </p>
 *
 * @author MartinAR
 */
public class DeportivApplication extends Application {

    /**
     * Método principal que inicia la aplicación JavaFX.
     *
     * @param stage El escenario principal donde se monta la escena.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeportivApplication.class.getResource("FlipLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("DeportivApp");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método main que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
