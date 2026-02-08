package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    public String currentlyLoggedUser = "";

    @FXML
    Label labelID;

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_edit_data-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        UserEditDataController controller = fxmlLoader.getController();
        controller.currentlyLoggedUser = this.currentlyLoggedUser;
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - User account");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onMangageReservationsClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_manage_reservations-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        UserEditDataController controller = fxmlLoader.getController();
        controller.currentlyLoggedUser = this.currentlyLoggedUser;
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - Manage Reservations");
        stage.setScene(scene);
        stage.show();
    }
}
