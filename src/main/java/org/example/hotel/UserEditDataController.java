package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Float.parseFloat;

/**
 * Window to edit basic information about the user currently logged in.
 */
public class UserEditDataController {
    Database database = new Database(); //TODO should be from file
    public String currentlyLoggedUser = "";

    @FXML
    TextField nameValue;
    @FXML
    TextField lastNameValue;
    @FXML
    TextField emailValue;
    @FXML
    TextField phoneValue;
    @FXML
    TextField oldPasswordValue;
    @FXML
    TextField newPasswordValue;

    public void initialize() {
        nameValue.setText(database.GetUserName(currentlyLoggedUser));
        lastNameValue.setText(database.GetUserName(currentlyLoggedUser));
        emailValue.setText(database.GetUserEmail(currentlyLoggedUser));
        phoneValue.setText(database.GetUserPhone(currentlyLoggedUser));
    }


    /**
     * @brief Saves user data if it is correct.
     * @details Highlights incorrect values in red without saving if those exist. Checks validity of some values.
     */
    @FXML
    protected void onSaveUserDataButtonClick() {
        boolean correct = true;
        nameValue.setStyle("-fx-border-color: black;");
        lastNameValue.setStyle("-fx-border-color: black;");
        emailValue.setStyle("-fx-border-color: black;");
        phoneValue.setStyle("-fx-border-color: black;");
        if (nameValue.getText().isEmpty()) {
            correct = false;
            nameValue.setStyle("-fx-border-color: red;");
        } else database.SetUserName(currentlyLoggedUser, nameValue.getText());
        if (lastNameValue.getText().isEmpty()) {
            correct = false;
            lastNameValue.setStyle("-fx-border-color: red;");
        } else database.SetUserLastName(currentlyLoggedUser, lastNameValue.getText());
        if (!emailValue.getText().isEmpty() && IsEmailValid(emailValue.getText())) {
            correct = false;
            emailValue.setStyle("-fx-border-color: red;");
        }  else database.SetUserEmail(currentlyLoggedUser, emailValue.getText());
        if (!phoneValue.getText().isEmpty() && phoneValue.getText().length() != 9) {
            correct = false;
            phoneValue.setStyle("-fx-border-color: red;");
        } else database.SetUserPhone(currentlyLoggedUser, phoneValue.getText());

        if (!oldPasswordValue.getText().isEmpty() && !newPasswordValue.getText().isEmpty()) {
            if (IsPasswordCorrect(oldPasswordValue.getText())) {
                if (oldPasswordValue.getText().equals(newPasswordValue.getText())) {
                    correct = false;
                    oldPasswordValue.setStyle("-fx-border-color: red;");
                    newPasswordValue.setStyle("-fx-border-color: red;");
                } else  {
                    database.SetUserPassword(currentlyLoggedUser, newPasswordValue.getText());
                }
            } else {
                correct = false;
                oldPasswordValue.setStyle("-fx-border-color: red;");
            }
        } else {
            oldPasswordValue.setStyle("-fx-border-color: red;");
            newPasswordValue.setStyle("-fx-border-color: red;");
        }

        if (correct) {
            Stage stage_this = (Stage) nameValue.getScene().getWindow();
            stage_this.close();
        }
    }

    /**
     * Reverts changes.
     */
    @FXML
    protected void onAbortUserDataButtonClick() {
        nameValue.setText(database.GetUserName(currentlyLoggedUser));
        lastNameValue.setText(database.GetUserName(currentlyLoggedUser));
        emailValue.setText(database.GetUserEmail(currentlyLoggedUser));
        phoneValue.setText(database.GetUserPhone(currentlyLoggedUser));
        oldPasswordValue.setText("");
        newPasswordValue.setText("");

        nameValue.setStyle("-fx-border-color: black;");
        lastNameValue.setStyle("-fx-border-color: black;");
        emailValue.setStyle("-fx-border-color: black;");
        phoneValue.setStyle("-fx-border-color: black;");
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Name() {
        if (nameValue.getText().equals(database.GetUserName(currentlyLoggedUser))) {
            nameValue.setStyle("-fx-border-color: black;");
        }  else {
            nameValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_LastName() {
        if (lastNameValue.getText().equals(database.GetUserName(currentlyLoggedUser))) {
            lastNameValue.setStyle("-fx-border-color: black;");
        }  else {
            lastNameValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Email() {
        if (emailValue.getText().equals(database.GetUserEmail(currentlyLoggedUser))) {
            emailValue.setStyle("-fx-border-color: black;");
        }  else {
            emailValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Checks if the value in the field is different from the one in database.
     * @details If it is, highlights the field with yellow outline.
     */
    @FXML
    protected void HasValueChanged_Phone() {
        if (phoneValue.getText().equals(database.GetUserPhone(currentlyLoggedUser))) {
            phoneValue.setStyle("-fx-border-color: black;");
        }  else {
            phoneValue.setStyle("-fx-border-color: yellow;");
        }
    }

    /**
     * @brief Simple email validity check.
     * @param email email string
     * @return true if the string contains '@', false otherwise.
     */
    private boolean IsEmailValid(String email) {
        for  (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user inputted their password.
     * @param oldPassword password in the oldPassword field.
     * @return true if password is correct.
     */
    private boolean IsPasswordCorrect(String oldPassword) {
        if (database.GetUserPassword(currentlyLoggedUser).equals(oldPassword)) {
            return true;
        }
        return false;
    }
}
