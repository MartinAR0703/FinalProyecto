package com.example.appfutbol.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.appfutbol.db.UserDao;
import com.example.appfutbol.models.usuario;

import java.io.IOException;

/**
 * Controlador para la vista de login y registro (FlipLogin).
 * Permite al usuario iniciar sesión o registrarse, alternando entre vistas con un interruptor visual (flipToggle).
 * Gestiona las interacciones con la base de datos usando {@link UserDao}.
 *
 * <p>Autor: MartinAR</p>
 */
public class FlipLogin {

    @FXML private CheckBox flipToggle;
    @FXML private VBox loginCard;
    @FXML private VBox signupCard;
    @FXML private Button loginButton;
    @FXML private Button signupButton;
    @FXML private TextField loginEmail;
    @FXML private PasswordField loginPassword;
    @FXML private TextField signName;
    @FXML private TextField signMail;
    @FXML private PasswordField signPassword;

    /**
     * Método que se ejecuta automáticamente al cargar la vista.
     * Configura el comportamiento del {@code flipToggle} para alternar entre las vistas de login y registro.
     */
    @FXML
    public void initialize() {
        loginCard.setVisible(true);
        loginCard.setManaged(true);
        signupCard.setVisible(false);
        signupCard.setManaged(false);

        flipToggle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            loginCard.setVisible(!isSelected);
            loginCard.setManaged(!isSelected);
            signupCard.setVisible(isSelected);
            signupCard.setManaged(isSelected);
        });
    }

    /**
     * Maneja el evento de clic en el botón de login.
     * Verifica las credenciales ingresadas por el usuario contra la base de datos.
     * Si son válidas, redirige a la pantalla principal (home.fxml).
     *
     * @param event Evento que representa el clic del botón
     * @throws IOException si ocurre un error al cargar la siguiente vista
     */
    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = loginEmail.getText();
        String password = loginPassword.getText();

        UserDao dao = new UserDao();
        boolean coincide = dao.login(email, password);

        if (coincide) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/appfutbol/Views/home.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "Correo o contraseña incorrectos.");
        }
    }

    /**
     * Maneja el evento de clic en el botón de registro.
     * Valida los campos de entrada y registra al nuevo usuario si todos los datos son válidos.
     *
     * @param event Evento que representa el clic del botón
     * @throws IOException si ocurre un error inesperado
     */
    @FXML
    public void onSignupButtonClick(ActionEvent event) throws IOException {
        String name = signName.getText();
        String email = signMail.getText();
        String password = signPassword.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios.");
            return;
        }

        UserDao dao = new UserDao();
        usuario nuevo = new usuario();
        nuevo.setNombre(name);
        nuevo.setCorreo(email);
        nuevo.setPassword(dao.sha1(password)); // Encriptación con SHA-1

        boolean guardado = dao.save(nuevo);
        if (guardado) {
            showAlert("Éxito", "Usuario registrado correctamente.");
            flipToggle.setSelected(false); // Volver al login
        } else {
            showAlert("Error", "No se pudo registrar el usuario.");
        }
    }

    /**
     * Muestra un mensaje emergente al usuario con el contenido especificado.
     *
     * @param title   Título del mensaje
     * @param message Contenido del mensaje
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
