package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Root admin view window. User can then choose whether to edit hotel data or edit users.
 */
public class AdminController {
    Database database = new Database();
    public String currentlyLoggedUser = "";

    @FXML
    Label labelID;

    /**
     * Opens editing hotel data window.
     * @throws IOException
     */
    @FXML
    protected void onEditHotelDataButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_data-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - Admin access");
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
        Scene scene = new Scene(fxmlLoader.load(), 480, 320);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems - Admin access");
        stage.setScene(scene);
        stage.setOnHiding(event -> {
            AdminEditUsersController controller = fxmlLoader.getController();
            controller.SaveToDatabase(database);
        });
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
}
