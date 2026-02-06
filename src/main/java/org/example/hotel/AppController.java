package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    /**
     * @brief Handles logging in.
     * @details Handles incorrect login. Opens admin view if user is an admin, normal otherwise.
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
                Stage stage = new Stage();
                stage.setTitle("Hotel Systems - Admin access");
                stage.setScene(scene);
                stage.show();
            } else {
                Stage stage_this = (Stage) systemText.getScene().getWindow();
                stage_this.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 480, 320);
                Stage stage = new Stage();
                stage.setTitle("Hotel Systems");
                stage.setScene(scene);
                stage.show();
            }
        } else {
            usernameArea.clear();
            passwordArea.clear();

            systemText.setText("Invalid username or password");
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
}

