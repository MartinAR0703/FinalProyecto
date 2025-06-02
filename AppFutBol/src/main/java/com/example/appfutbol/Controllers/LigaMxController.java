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

public class LigaMxController {
    @FXML
    private TableView<partido> siguientesPartidos;
    @FXML
    private TableColumn<partido, String> colLocal;
    @FXML
    private TableColumn<partido, String> colVisitante;
    @FXML
    private TableColumn<partido, String> colFecha;
    @FXML
    public TableView<Jugador> tablaJugadores;
    @FXML
    public TableView<Equipo> tablaPosiciones;
    @FXML
    private TableColumn<Equipo, String> colNombre;
    @FXML
    private TableColumn<Equipo, String> colGanados;
    @FXML
    private TableColumn<Equipo, String> colEmpates;
    @FXML
    private TableColumn<Equipo, String> colPerdidos;
    @FXML
    private TableColumn<Equipo, String> colPuntos;
    @FXML
    private TableColumn<Jugador, String> colNombreJugador;
    @FXML
    private TableColumn<Jugador, String> colGoles;
    @FXML
    private TableColumn<Jugador, String> colPartidos;
    @FXML
    private TableColumn<Jugador, String> colEquipo;


    @FXML
    public void initialize() {
        colLocal.setCellValueFactory(new PropertyValueFactory<>("equipoLocal"));
        colVisitante.setCellValueFactory(new PropertyValueFactory<>("equipoVisitante"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGanados.setCellValueFactory(new PropertyValueFactory<>("ganados"));
        colEmpates.setCellValueFactory(new PropertyValueFactory<>("empates"));
        colPerdidos.setCellValueFactory(new PropertyValueFactory<>("perdidos"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
        colNombreJugador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        colPartidos.setCellValueFactory(new PropertyValueFactory<>("partidos"));
        colEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));

        try {
            List<partido> partidos = LigaMxService.obtenerPartidos();
            siguientesPartidos.getItems().addAll(partidos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EspnPosicionesMX2024Strategy strategy = new EspnPosicionesMX2024Strategy();
        List<Equipo> equipos = strategy.execute();

        tablaPosiciones.setItems(FXCollections.observableArrayList(equipos));

        EspnJugadorMX2024Strategy scraper = new EspnJugadorMX2024Strategy();
        List<Jugador> jugadores = scraper.execute();
        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(data);


    }

    @FXML
    public void btnHomeClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}