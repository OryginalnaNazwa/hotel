package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminEditUserController {
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label adminLabel;
    @FXML private Label nameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label errorLabel;
    @FXML private Button saveButton;
    @FXML private Button abortButton;

    @FXML private TextField usernameValue;
    @FXML private TextField passwordValue;
    @FXML private TextField nameValue;
    @FXML private TextField lastNameValue;
    @FXML private TextField emailValue;
    @FXML private TextField phoneValue;
    @FXML private CheckBox adminValue;

    public User editedUser;
    private boolean isNewUser = false;
    Database database = new Database();

    @FXML
    void initialize() {
        // Load translations
        usernameLabel.setText(Language.get("username"));
        passwordLabel.setText(Language.get("password"));
        adminLabel.setText(Language.get("admin"));
        nameLabel.setText(Language.get("name"));
        lastNameLabel.setText(Language.get("lastName"));
        emailLabel.setText(Language.get("email"));
        phoneLabel.setText(Language.get("phone"));
        saveButton.setText(Language.get("save"));
        abortButton.setText(Language.get("abort"));
    }

    public void loadUser(User user) {
        if (user == null) {
            isNewUser = true;
            editedUser = new User();
        } else {
            editedUser = user;
            usernameValue.setText(user.username);
            passwordValue.setText(user.password);
            nameValue.setText(user.name);
            lastNameValue.setText(user.lastName);
            emailValue.setText(user.email);
            phoneValue.setText(user.phone);
            adminValue.setSelected(user.admin);
        }
    }

    @FXML
    void onSaveClick() {
        errorLabel.setText("");

        // Get values
        String username = usernameValue.getText().trim();
        String password = passwordValue.getText().trim();
        String name = nameValue.getText().trim();
        String lastName = lastNameValue.getText().trim();
        String email = emailValue.getText().trim();
        String phone = phoneValue.getText().trim();
        boolean admin = adminValue.isSelected();

        // Validation
        if (username.isEmpty()) {
            errorLabel.setText(Language.get("error_empty_username"));
            return;
        }
        if (password.isEmpty()) {
            errorLabel.setText(Language.get("error_empty_password"));
            return;
        }
        if (name.isEmpty()) {
            errorLabel.setText(Language.get("error_empty_name"));
            return;
        }
        if (lastName.isEmpty()) {
            errorLabel.setText(Language.get("error_empty_lastname"));
            return;
        }

        // Check if username already exists (only for new users)
        if (isNewUser) {
            for (User u : database.users) {
                if (u.username.equals(username)) {
                    errorLabel.setText(Language.get("error_username_exists"));
                    return;
                }
            }
        }

        // Save to user
        editedUser.username = username;
        editedUser.password = password;
        editedUser.name = name;
        editedUser.lastName = lastName;
        editedUser.email = email;
        editedUser.phone = phone;
        editedUser.admin = admin;

        if (isNewUser) {
            database.users.add(editedUser);
        }

        FileOperations.saveUsers(database.users);

        // Close window
        Stage stage = (Stage) usernameValue.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onAbortClick() {
        Stage stage = (Stage) usernameValue.getScene().getWindow();
        stage.close();
    }
}
