package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Float.parseFloat;

/**
 * Window to edit basic information about the hotel.
 */
public class AdminEditDataController {
    Database database = new Database();

    @FXML
    TextField hotelNameValue;
    @FXML
    TextField hotelAddressValue;
    @FXML
    TextField hotelMultiplierValue;
    @FXML
    Spinner<Integer> hotelStarsValue;
    @FXML
    Spinner<Integer> hotelFloorsValue;

    public void initialize() {
        hotelNameValue.setText(database.hotel.name);
        hotelAddressValue.setText(database.hotel.address);
        hotelMultiplierValue.setText(database.hotel.defaultPremiumMultiplier.toString());
        SpinnerValueFactory<Integer> spinnerValuefactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5);
        hotelStarsValue.setValueFactory(spinnerValuefactory);
        if (database.hotel.stars > 1) hotelStarsValue.increment(database.hotel.stars - 1);
        spinnerValuefactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,99);
        hotelFloorsValue.setValueFactory(spinnerValuefactory);
        if (database.hotel.floors > 1) hotelFloorsValue.increment(database.hotel.floors - 1);
    }


    /**
     * @brief Saves hotel data if it is correct.
     * @details Highlights incorrect values in red without saving if those exist.
     */
    @FXML
    protected void onSaveHotelDataButtonClick() {
        boolean correct = true;
        hotelNameValue.setStyle("-fx-border-color: black;");
        hotelAddressValue.setStyle("-fx-border-color: black;");
        hotelMultiplierValue.setStyle("-fx-border-color: black;");
        if (hotelNameValue.getText().isEmpty()) {
            correct = false;
            hotelNameValue.setStyle("-fx-border-color: red;");
        } else database.hotel.name = hotelNameValue.getText();
        if (hotelAddressValue.getText().isEmpty()) {
            correct = false;
            hotelAddressValue.setStyle("-fx-border-color: red;");
        } else database.hotel.address = hotelAddressValue.getText();

        if (parseFloat(hotelMultiplierValue.getText()) < 1.0) {
            correct = false;
            hotelMultiplierValue.setStyle("-fx-border-color: red;");
        } else database.hotel.defaultPremiumMultiplier = parseFloat(hotelMultiplierValue.getText());
        database.hotel.stars = hotelStarsValue.getValue();
        database.hotel.floors = hotelFloorsValue.getValue();

        if (correct) {
            database.fileOp.saveHotel(database.hotel);
            Stage stage_this = (Stage) hotelFloorsValue.getScene().getWindow();
            stage_this.close();
        }
    }

    /**
     * Reverts changes.
     */
    @FXML
    protected void onAbortHotelDataButtonClick() {
        hotelNameValue.setText(database.hotel.name);
        hotelAddressValue.setText(database.hotel.address);
        hotelMultiplierValue.setText(database.hotel.defaultPremiumMultiplier.toString());
        hotelStarsValue.getValueFactory().setValue(database.hotel.stars);
        hotelFloorsValue.getValueFactory().setValue(database.hotel.floors);
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Name() {
        if (hotelNameValue.getText().equals(database.hotel.name)) {
            hotelNameValue.setStyle("-fx-border-color: black;");
        }  else {
            hotelNameValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Address() {
        if (hotelAddressValue.getText().equals(database.hotel.address)) {
            hotelAddressValue.setStyle("-fx-border-color: black;");
        }  else {
            hotelAddressValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Stars() {
        if (hotelStarsValue.getValue().equals(database.hotel.stars)) {
            hotelStarsValue.setStyle("-fx-border-color: black;");
        }  else {
            hotelStarsValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Floors() {
        if (hotelFloorsValue.getValue().equals(database.hotel.floors)) {
            hotelFloorsValue.setStyle("-fx-border-color: black;");
        }  else {
            hotelFloorsValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Multiplier() {
        if (hotelMultiplierValue.getText().equals(database.hotel.defaultPremiumMultiplier.toString())) {
            hotelNameValue.setStyle("-fx-border-color: black;");
        }  else {
            hotelNameValue.setStyle("-fx-border-color: yellow;");
        }
    }
}
