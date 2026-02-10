package org.example.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Admin only window of viewing and editing user data in a table.
 */
public class AdminEditUsersController {
    public Button addUserButton;
    public Button editUserButton;
    public Button deleteUserButton;
    public Button saveButton;
    public Button abortButton;
    Database database = new Database();

    @FXML
    private TableView<User> Tabela;
    @FXML private TableColumn<User, String> usernameCol;
    @FXML private TableColumn<User, String> nameCol;
    @FXML private TableColumn<User, String> lastNameCol;
    @FXML private TableColumn<User, Boolean> adminCol;
    @FXML private TableColumn<User, String> emailCol;
    @FXML private TableColumn<User, String> phoneCol;

    @FXML
    public void initialize(){
        usernameCol.setText(Language.get("username"));
        nameCol.setText(Language.get("name"));
        lastNameCol.setText(Language.get("lastName"));
        adminCol.setText(Language.get("admin"));
        emailCol.setText(Language.get("email"));
        phoneCol.setText(Language.get("phone"));
        addUserButton.setText(Language.get("add_user"));
        deleteUserButton.setText(Language.get("delete_user"));
        saveButton.setText(Language.get("save"));
        abortButton.setText(Language.get("abort"));
        editUserButton.setText(Language.get("edit_user"));

        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        Tabela.setItems(database.users);
    }

    @FXML
    protected void addUser() throws IOException {
        Stage stage_this = (Stage) addUserButton.getScene().getWindow();
        stage_this.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        AdminEditUserController controller = fxmlLoader.getController();
        controller.loadUser(null);
        Stage stage = new Stage();
        stage.setTitle(Language.get("add_user"));
        stage.setScene(scene);
        stage.setOnHiding(event -> {
            stage_this.show();
            database.users = FileOperations.loadUsers();
            Tabela.refresh();
        });
        stage.show();
    }

    @FXML
    public void onEditUserButtonClick() throws IOException {
        int index = Tabela.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Stage stage_this = (Stage) addUserButton.getScene().getWindow();
            stage_this.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_edit_user-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 600);
            AdminEditUserController controller = fxmlLoader.getController();
            controller.loadUser(database.users.get(index));
            Stage stage = new Stage();
            stage.setTitle(Language.get("edit_data"));
            stage.setScene(scene);
            stage.setOnHiding(event -> {
                stage_this.show();
                database.users = FileOperations.loadUsers();
                Tabela.refresh();
            });
            stage.show();
        }
    }

    @FXML
    public void onDeleteUserButtonClick()  {
        int index = Tabela.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            database.users.remove(Tabela.getItems().get(index));
            Tabela.refresh();
        }
    }

    @FXML
    public void onSaveButtonClick() {
        FileOperations.saveUsers(database.users);
        Stage stage_this = (Stage) addUserButton.getScene().getWindow();
        stage_this.close();
    }

    @FXML
    public void onAbortButtonClick() {
        Stage stage_this = (Stage) addUserButton.getScene().getWindow();
        stage_this.close();
    }
}