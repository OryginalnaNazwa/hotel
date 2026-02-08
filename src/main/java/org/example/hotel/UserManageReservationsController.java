package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UserManageReservationsController {
    @FXML
    private Label personsValue;
    @FXML
    private Label premiumValue;
    @FXML
    private Label beginDateValue;
    @FXML
    private Label endDateValue;
    @FXML
    private Label floorValue;
    @FXML
    private HBox roomsMap;

    Integer currentFloor = 1;
    String currentlyLoggedUser = "";
    Database database = new Database(); //TODO should be from file

    @FXML
    void initialize() {
        floorValue.setText(currentFloor.toString());
        MapRawFloor();
    }

    private void MapRawFloor() {
        roomsMap.getChildren().clear();
        floorValue.setText(currentFloor.toString());
        for (Room room : database.hotel.rooms) {
            if (room.floor == currentFloor) {
                Rectangle aRoom = new Rectangle(40,40);
                aRoom.setFill(Color.WHITE);
                aRoom.setStroke(Color.BLACK);
                aRoom.setStrokeWidth(2);

                roomsMap.getChildren().add(aRoom);
            }
        }
    }

    @FXML
    void IncreaseFloor() {
        if (currentFloor < database.hotel.floors) {
            currentFloor++;
        }
        MapRawFloor();
    }

    @FXML
    void DecreaseFloor() {
        if (currentFloor > 0) {
            currentFloor--;
        }
        MapRawFloor();
    }
}
