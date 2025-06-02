package com.example.appfutbol.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.appfutbol.Utils.LigaMX.EspnJugadorMX2024Strategy;
import com.example.appfutbol.Utils.LigaMX.EspnPosicionesMX2024Strategy;
import com.example.appfutbol.Utils.LigaMX.LigaMxService;
import com.example.appfutbol.models.Equipo;
import com.example.appfutbol.models.Jugador;
import com.example.appfutbol.models.partido;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para la vista de estadísticas de la Liga MX.
 * Muestra la tabla de posiciones, los jugadores destacados y los próximos partidos.
 * Utiliza estrategias de scraping personalizadas para obtener la información desde ESPN.
 *
 *  @author MartinAR
 */
public class LigaMxController {

    @FXML private TableView<partido> siguientesPartidos;
    @FXML private TableColumn<partido, String> colLocal;
    @FXML private TableColumn<partido, String> colVisitante;
    @FXML private TableColumn<partido, String> colFecha;

    @FXML public TableView<Jugador> tablaJugadores;
    @FXML public TableView<Equipo> tablaPosiciones;

    @FXML private TableColumn<Equipo, String> colNombre;
    @FXML private TableColumn<Equipo, String> colGanados;
    @FXML private TableColumn<Equipo, String> colEmpates;
    @FXML private TableColumn<Equipo, String> colPerdidos;
    @FXML private TableColumn<Equipo, String> colPuntos;

    @FXML private TableColumn<Jugador, String> colNombreJugador;
    @FXML private TableColumn<Jugador, String> colGoles;
    @FXML private TableColumn<Jugador, String> colPartidos;
    @FXML private TableColumn<Jugador, String> colEquipo;

    /**
     * Inicializa los datos de la vista al cargarla.
     * Se configuran las columnas de las tablas y se llenan con los datos extraídos de ESPN.
     */
    @FXML
    public void initialize() {
        // Configurar columnas de partidos
        colLocal.setCellValueFactory(new PropertyValueFactory<>("equipoLocal"));
        colVisitante.setCellValueFactory(new PropertyValueFactory<>("equipoVisitante"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Configurar columnas de posiciones
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGanados.setCellValueFactory(new PropertyValueFactory<>("ganados"));
        colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        colPerdidos.setCellValueFactory(new PropertyValueFactory<>("perdidos"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        // Configurar columnas de jugadores
        colNombreJugador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        colPartidos.setCellValueFactory(new PropertyValueFactory<>("partidos"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));

        // Cargar próximos partidos
        try {
            List<partido> partidos = LigaMxService.obtenerPartidos();
            siguientesPartidos.getItems().addAll(partidos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cargar tabla de posiciones
        EspnPosicionesMX2024Strategy strategy = new EspnPosicionesMX2024Strategy();
        List<Equipo> equipos = strategy.execute();
        tablaPosiciones.setItems(FXCollections.observableArrayList(equipos));

        // Cargar tabla de jugadores
        EspnJugadorMX2024Strategy scraper = new EspnJugadorMX2024Strategy();
        List<Jugador> jugadores = scraper.execute();
        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(data);
    }

    /**
     * Maneja el evento de clic en el botón "Home".
     * Redirige al usuario a la vista principal del sistema.
     *
     * @param actionEvent Evento del botón
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    public void btnHomeClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
