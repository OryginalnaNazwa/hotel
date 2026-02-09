package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Admin only window of viewing and editing user data.
 */
public class AdminEditRoomsController {
    public Button addRoomButton;
    public Button editRoomButton;
    public Button deleteRoomButton;
    public Button saveButton;
    public Button abortButton;
    Database database = new Database();

    @FXML
    private TableView Tabela;
    @FXML private TableColumn<Room, Boolean> premiumCol;
    @FXML private TableColumn<Room, String> numberCol;
    @FXML private TableColumn<Room, Integer> floorCol;
    @FXML private TableColumn<Room, Integer> bedsCol;
    @FXML private TableColumn<Room, Float> priceCol;
    @FXML private TableColumn<Room, Integer> idCol;


    @FXML
    public void initialize(){
        premiumCol.setText(Language.get("premium"));
        numberCol.setText(Language.get("number"));
        floorCol.setText(Language.get("floor"));
        bedsCol.setText(Language.get("beds"));
        priceCol.setText(Language.get("price"));
        idCol.setText(Language.get("id"));
        addRoomButton.setText(Language.get("add_room"));
        deleteRoomButton.setText(Language.get("delete_room"));
        editRoomButton.setText(Language.get("edit_room"));
        saveButton.setText(Language.get("save"));
        abortButton.setText(Language.get("abort"));

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        bedsCol.setCellValueFactory(new PropertyValueFactory<>("beds"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        premiumCol.setCellValueFactory(new PropertyValueFactory<>("premium"));

        Tabela.setItems(database.hotel.rooms);
    }

    /**
     * Adds a new room.
     */
    @FXML
    protected void addRoom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_room.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 620);
        AdminEditRoomController controller = fxmlLoader.getController();
        controller.loadRoom(null);
        Stage stage = new Stage();
        stage.setTitle("Hotel Systems");
        stage.setScene(scene);
        stage.setOnHiding(event -> {
            Tabela.refresh();
        });
        stage.show();
    }

    @FXML
    public void onEditRoomButtonClick() throws IOException {
        int index = Tabela.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_room.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 780, 620);
            AdminEditRoomController controller = fxmlLoader.getController();
            controller.loadRoom(database.hotel.rooms.get(index));
            Stage stage = new Stage();
            stage.setTitle("Hotel Systems");
            stage.setScene(scene);
            stage.setOnHiding(event -> {
                Tabela.refresh();
            });
            stage.show();
        }
    }

    @FXML
    public void onDeleteRoomButtonClick()  {
        int index = Tabela.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            database.hotel.rooms.remove(Tabela.getItems().get(index));
            Tabela.refresh();
        }
    }

    @FXML
    public void onSaveButtonClick() {
        FileOperations.saveRooms(database.hotel.rooms);
        Stage stage_this = (Stage) addRoomButton.getScene().getWindow();
        stage_this.close();
    }

    @FXML
    public void onAbortButtonClick() {
        Stage stage_this = (Stage) addRoomButton.getScene().getWindow();
        stage_this.close();
    }
}

