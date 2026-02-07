package org.example.hotel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * Admin only window of viewing and editing user data.
 */
public class AdminEditUsersController {
    Database database = new Database();

    @FXML
    private TableView Tabela;
    @FXML private TableColumn<User, Boolean> adminCol;
    @FXML private TableColumn<User, String> usernameCol;
    @FXML private TableColumn<User, String> nameCol;
    @FXML private TableColumn<User, String> lastNameCol;
    //@FXML private TableColumn<User, String> wypozyczaczCol;

    @FXML
    public void initialize(){
        usernameCol.setCellFactory(
                TextFieldTableCell.forTableColumn());

        nameCol.setCellFactory(
                TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(
                TextFieldTableCell.forTableColumn());

        adminCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        //wypozyczaczCol.setCellValueFactory(new PropertyValueFactory<>("wypozyczacz"));
        //usernameCol.setCellFactory(new Ca);

        Tabela.setItems(database.users);
    }

    @FXML
    protected void setFieldUsername(String username){

    }

    /**
     * @brief Adds new user.
     * @details New user has username "user", password "user" and is not an admin. Change these immediately with edit users.
     */
    @FXML
    protected void addUser(){
        database.users.add(new User("user","user", false));
        Tabela.setItems(database.users);
        Tabela.setEditable(true);
    }

    public void SaveToDatabase(Database database_out) {
        database_out.users.clear();
        database_out.users.addAll(Tabela.getSelectionModel().getSelectedItems());
    }
}
