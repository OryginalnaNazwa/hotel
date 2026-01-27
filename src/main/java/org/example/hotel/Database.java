package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Database {
    public ObservableList<User> users = FXCollections.observableArrayList();
    public Hotel hotel = new Hotel("HotelX", 3, 1, 2, 2, 1);

    public Database() {
        users.add(new User("admin", "admin", true));
    }

    public boolean IsLoginAttemptCorrect(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean IsUserAdmin(String username) {
        boolean admin = false;
        for (User user : users) {
            if (user.username.equals(username)) {
                admin = user.admin;
            }
        }
        return admin;
    }
}

