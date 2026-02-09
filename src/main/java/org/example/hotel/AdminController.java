package org.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Root admin view window. User can then choose whether to edit hotel data or edit users.
 */
public class AdminController {


    public Button editDataButton;
    public Button manageUsersButton;
    public Button logOutButton;
    public Button settingsButton;
    public Button editRoomsButton;
    Database database = new Database();
    public String currentlyLoggedUser = "";

    @FXML
    Label labelID;

    @FXML
    void initialize() {
        editDataButton.setText(Language.get("edit_hotel_data"));
        manageUsersButton.setText(Language.get("manage_users"));
        logOutButton.setText(Language.get("logout"));
        labelID.setText(Language.get("admin_view"));
        settingsButton.setText(Language.get("settings"));
        editRoomsButton.setText(Language.get("edit_rooms"));
    }

    /**
     * Opens editing hotel data window.
     * @throws IOException
     */
    @FXML
    protected void onEditHotelDataButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_data-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - Hotel");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens user managing window.
     * @throws IOException
     */
    @FXML
    protected void onManageUsersButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_users-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 620);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes this window and opens the login screen.
     * @throws IOException
     */
    @FXML
    protected void onLogOutButtonClick() throws IOException {
        Stage stage_this = (Stage) labelID.getScene().getWindow();
        stage_this.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems");
        stage.setScene(scene);
        stage.show();
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

    public void onEditRoomsButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_rooms-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 620);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems");
        stage.setScene(scene);
        stage.show();
    }
}
