package org.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SettingsController {

    @FXML
    public Label settingsLabel;
    public Label languageLabel;
    public Button saveButton;
    public Button rejectButton;
    public ChoiceBox<String> languageChoice;

    @FXML
    public void initialize() {
        settingsLabel.setText(Language.get("settings"));
        languageLabel.setText(Language.get("language"));
        saveButton.setText(Language.get("save"));
        rejectButton.setText(Language.get("abort"));

        languageChoice.setValue(Language.get("language"));
    }

    @FXML
    public void onSaveButtonClick(ActionEvent actionEvent) {

    }

    @FXML
    public void onRejectButtonClick(ActionEvent actionEvent) {

    }
}
