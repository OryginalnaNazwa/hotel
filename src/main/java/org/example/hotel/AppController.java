package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Log-in window
 */
public class AppController {

    Database database = new Database();

    @FXML
    private Label systemText;
    @FXML
    private TextField usernameArea;
    @FXML
    private PasswordField passwordArea;
    @FXML
    private Button loginButton;
    @FXML
    private Button settingsButton;

    @FXML
    void initialize() {
        FileOperations.loadConfig();
        passwordArea.setPromptText(Language.get("password"));
        systemText.setText(Language.get("system_welcome"));
        loginButton.setText(Language.get("login"));
        settingsButton.setText(Language.get("settings"));
    }

    /**
     * Handles logging in.
     * Handles incorrect login. Opens admin view if user is an admin, normal otherwise.
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        systemText.setText("");
        String login = usernameArea.getText();
        String password = passwordArea.getText();

        if (database.IsLoginAttemptCorrect(login, password)) {
            usernameArea.clear();
            passwordArea.clear();

            if (database.IsUserAdmin(login)) {
                Stage stage_this = (Stage) systemText.getScene().getWindow();
                stage_this.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 480, 320);
                AdminController controller = fxmlLoader.getController();
                controller.currentlyLoggedUser = login;
                Stage stage = new Stage();
                stage.setTitle("Hotel Systems - " + Language.get("admin_access"));
                stage.setScene(scene);
                stage.show();
            } else {
                Stage stage_this = (Stage) systemText.getScene().getWindow();
                stage_this.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 480, 320);
                UserController controller = fxmlLoader.getController();
                controller.currentlyLoggedUser = login;
                Stage stage = new Stage();
                stage.setTitle("Hotel Systems");
                stage.setScene(scene);
                stage.show();
            }
        } else {
            usernameArea.clear();
            passwordArea.clear();

            systemText.setText(Language.get("system_invalid"));
        }
    }

    /**
     * Checks if enter key is pressed - for logging without button press.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void isEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            onLoginButtonClick();
        }
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 620);
        Stage stage = new Stage();
        stage.setTitle(Language.get("settings"));
        stage.setScene(scene);
        stage.show();
    }
}

