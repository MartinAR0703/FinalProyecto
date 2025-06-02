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
import com.example.appfutbol.Utils.SerieA.EspnJugadorSA2024Strategy;
import com.example.appfutbol.Utils.SerieA.EspnPosicionesSA2024Strategy;
import com.example.appfutbol.Utils.SerieA.SerieaService;
import com.example.appfutbol.models.Equipo;
import com.example.appfutbol.models.Jugador;
import com.example.appfutbol.models.partido;

import java.io.IOException;
import java.util.List;

/**
 * Controlador de la vista Serie A.
 * Carga y muestra las estadísticas de la temporada 2024 de la Serie A:
 * - Próximos partidos
 * - Tabla de posiciones
 * - Jugadores destacados
 *
 * <p>Autor: MartinAR</p>
 */
public class serieAController {

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
     * Inicializa las tablas con los datos obtenidos mediante scraping desde ESPN.
     * Se muestran los próximos partidos, la tabla de posiciones y los jugadores más destacados.
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

        // Cargar próximos partidos (aunque llama a Bundesliga, se asume Serie A por nombre del controller)
        try {
            List<partido> partidos = SerieaService.obtenerPartidosBundesliga(2024); // Verifica si es correcto el método
            siguientesPartidos.getItems().addAll(partidos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cargar tabla de posiciones
        EspnPosicionesSA2024Strategy strategy = new EspnPosicionesSA2024Strategy();
        List<Equipo> equipos = strategy.execute();
        tablaPosiciones.setItems(FXCollections.observableArrayList(equipos));

        // Cargar tabla de jugadores
        EspnJugadorSA2024Strategy scraper = new EspnJugadorSA2024Strategy();
        List<Jugador> jugadores = scraper.execute();
        ObservableList<Jugador> data = FXCollections.observableArrayList(jugadores);
        tablaJugadores.setItems(data);

        // Debug
        System.out.println("Jugadores encontrados: " + jugadores.size());
        jugadores.forEach(j -> System.out.println(j.getNombre()));
        System.out.println("Equipos cargados: " + equipos.size());
    }

    /**
     * Maneja el evento del botón "Home".
     * Redirige a la vista principal del sistema.
     *
     * @param actionEvent Evento generado al hacer clic
     * @throws IOException Si falla la carga del FXML
     */
    @FXML
    public void btnHomeClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/home.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
