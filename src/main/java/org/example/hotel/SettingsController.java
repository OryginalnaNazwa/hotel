package org.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

        if (Language.getCurrentLanguage().equals("en")) {
            languageChoice.setValue("English");
        } else if (Language.getCurrentLanguage().equals("pl")) {
            languageChoice.setValue("Polski");
        } else {
            languageChoice.setValue("No language");
        }

    }

    @FXML
    public void onSaveButtonClick() {
        if (languageChoice.getValue().equals("English")) {
            Language.currentLanguage = "en";
        }  else if (languageChoice.getValue().equals("Polski")) {
            Language.currentLanguage = "pl";
        } else  {
            Language.currentLanguage = "en";
        }
        FileOperations.saveConfig();
        Stage stage_this = (Stage) settingsLabel.getScene().getWindow();
        stage_this.close();
    }

    @FXML
    public void onRejectButtonClick() {
        Stage stage_this = (Stage) settingsLabel.getScene().getWindow();
        stage_this.close();
    }
}
