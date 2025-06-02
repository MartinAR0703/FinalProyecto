package com.example.appfutbol.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.appfutbol.db.UserDao;
import com.example.appfutbol.models.usuario;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.util.Optional;

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

    @FXML
    public void initialize() {
        // Mostrar login por defecto
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

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = loginEmail.getText();
        String password = loginPassword.getText();

        UserDao dao = new UserDao();
        boolean coincide = dao.login(email, password);

        if (coincide) {
            // Ir a la vista principal
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
        nuevo.setPassword(dao.sha1(password)); // usa el método del DAO
        // puedes guardar el nombre también si lo agregas al modelo y la tabla

        boolean guardado = dao.save(nuevo);
        if (guardado) {
            showAlert("Éxito", "Usuario registrado correctamente.");
            flipToggle.setSelected(false); // Regresa a login
        } else {
            showAlert("Error", "No se pudo registrar el usuario.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}