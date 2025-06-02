package com.example.appfutbol.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.appfutbol.Utils.BundesLiga.BundesligaService;
import com.example.appfutbol.Utils.BundesLiga.EspnJugadorBU2024Strategy;
import com.example.appfutbol.Utils.BundesLiga.EspnPosicionesBU2024Strategy;
import com.example.appfutbol.models.Equipo;
import com.example.appfutbol.models.Jugador;
import com.example.appfutbol.models.partido;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para la vista de la Bundesliga en la aplicación.
 * Gestiona la carga y visualización de partidos, posiciones y jugadores.
 *
 * @author MartinAR
 */
public class bundesLigaController {
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

    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Configura las columnas de las tablas y carga datos de partidos, equipos y jugadores.
     */
    @FXML
    public void initialize() {
        // Configura las columnas para que obtengan los valores de las propiedades del modelo
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

        // Carga los partidos próximos mediante la llamada al servicio BundesligaService
        try {
            List<partido> partidos = BundesligaService.obtenerPartidosBundesliga(2024);
            siguientesPartidos.getItems().addAll(partidos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtiene y muestra la tabla de posiciones de equipos usando la estrategia EspnPosicionesBU2024Strategy
        EspnPosicionesBU2024Strategy strategy = new EspnPosicionesBU2024Strategy();
        List<Equipo> equipos = strategy.execute();
        tablaPosiciones.setItems(FXCollections.observableArrayList(equipos));

        // Obtiene y muestra la lista de jugadores destacados usando EspnJugadorBU2024Strategy
        EspnJugadorBU2024Strategy scraper = new EspnJugadorBU2024Strategy();
        List<Jugador> jugadores = scraper.execute();
        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(data);
    }

    /**
     * Evento para regresar a la vista principal (home).
     *
     * @param actionEvent Evento generado por la acción del usuario (clic en botón).
     * @throws IOException si ocurre un error al cargar la vista.
     */
    @FXML
    public void btnHomeClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow(); // Obtiene el stage actual
        stage.setScene(new Scene(root));
        stage.show();
    }
}
