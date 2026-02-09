package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    public String currentlyLoggedUser = "";

    @FXML
    Label labelID;
    @FXML
    Button makeReservationsButton;
    @FXML
    Button editDataButton;
    @FXML
    Button logOutButton;
    @FXML
    Button settingsButton;

    @FXML
    void initialize() {
        makeReservationsButton.setText(Language.get("make_reservations"));
        editDataButton.setText(Language.get("edit_data"));
        logOutButton.setText(Language.get("logout"));
        settingsButton.setText(Language.get("settings"));
    }

    @FXML
    protected void onLogOutButtonClick() throws IOException {
        Stage stage_this = (Stage) labelID.getScene().getWindow();
        currentlyLoggedUser = "";
        stage_this.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onEditYourDataClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-edit_data-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        UserEditDataController controller = fxmlLoader.getController();
        controller.currentlyLoggedUser = this.currentlyLoggedUser;
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - " + Language.get("user_account"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onMangageReservationsClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_manage_reservations-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 680);
        UserManageReservationsController controller = fxmlLoader.getController();
        controller.currentlyLoggedUser = this.currentlyLoggedUser;
        controller.GetReservations();
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - " + Language.get("manage_reservations"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        Stage stage = new Stage();
        stage.setTitle(Language.get("settings"));
        stage.setScene(scene);
        stage.show();
    }
}
