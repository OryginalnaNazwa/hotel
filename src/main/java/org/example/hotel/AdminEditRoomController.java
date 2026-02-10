package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminEditRoomController {
    @FXML private Label idLabel;
    @FXML private Label numberLabel;
    @FXML private Label floorLabel;
    @FXML private Label premiumLabel;
    @FXML private Label bedsLabel;
    @FXML private Label priceLabel;
    @FXML private Label errorLabel;
    @FXML private Button saveButton;
    @FXML private Button abortButton;

    @FXML private TextField idValue;
    @FXML private TextField numberValue;
    @FXML private TextField floorValue;
    @FXML private TextField bedsValue;
    @FXML private TextField priceValue;
    @FXML private CheckBox premiumValue;

    public Room editedRoom;
    private boolean isNewRoom = false;
    Database database = new Database();

    @FXML
    void initialize() {
        // Load translations
        idLabel.setText(Language.get("id"));
        numberLabel.setText(Language.get("room_number"));
        floorLabel.setText(Language.get("floor"));
        premiumLabel.setText(Language.get("premium"));
        bedsLabel.setText(Language.get("beds"));
        priceLabel.setText(Language.get("price"));
        saveButton.setText(Language.get("save"));
        abortButton.setText(Language.get("abort"));
    }

    public void loadRoom(Room room) {
        if (room == null) {
            isNewRoom = true;
            editedRoom = new Room();
            Integer newId = database.hotel.rooms.size() + 1;
            editedRoom.setId(newId);
            idValue.setText(newId.toString());
        } else {
            editedRoom = room;
            idValue.setText(String.valueOf(room.id));
            numberValue.setText(room.number);
            floorValue.setText(String.valueOf(room.floor));
            bedsValue.setText(String.valueOf(room.beds));
            priceValue.setText(String.valueOf(room.price));
            premiumValue.setSelected(room.premium);
        }
    }

    @FXML
    void onSaveClick() {
        errorLabel.setText("");

        // Validate and parse
        try {
            int id = Integer.parseInt(idValue.getText().trim());
            String number = numberValue.getText().trim();
            int floor = Integer.parseInt(floorValue.getText().trim());
            int beds = Integer.parseInt(bedsValue.getText().trim());
            float price = Float.parseFloat(priceValue.getText().trim());
            boolean premium = premiumValue.isSelected();

            // Validation
            if (number.isEmpty()) {
                errorLabel.setText(Language.get("error_empty_number"));
                return;
            }
            if (floor < 1) {
                errorLabel.setText(Language.get("error_invalid_floor"));
                return;
            }
            if (beds < 1) {
                errorLabel.setText(Language.get("error_invalid_beds"));
                return;
            }
            if (price < 0) {
                errorLabel.setText(Language.get("error_invalid_price"));
                return;
            }

            // Save to room
            editedRoom.id = id;
            editedRoom.number = number;
            editedRoom.floor = floor;
            editedRoom.beds = beds;
            editedRoom.price = price;
            editedRoom.premium = premium;

            if (isNewRoom) {
                database.hotel.rooms.add(editedRoom);
            } else {
                for (int i = 0; i < database.hotel.rooms.size(); i++) {
                    if (database.hotel.rooms.get(i).id == editedRoom.id) {
                        database.hotel.rooms.set(i, editedRoom);
                        break;
                    }
                }
            }

            FileOperations.saveRooms(database.hotel.rooms);

            // Close window
            Stage stage = (Stage) idValue.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            errorLabel.setText(Language.get("error_invalid_numbers"));
        }
    }

    @FXML
    void onAbortClick() {
        Stage stage = (Stage) idValue.getScene().getWindow();
        stage.close();
    }
}
