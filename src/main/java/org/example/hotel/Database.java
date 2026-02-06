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

    /**
     * Checks if user inputted correct username and password.
     * @param username user's inputted username
     * @param password user's inputted password
     * @return true if login and username matches those in database, false otherwise
     */
    public boolean IsLoginAttemptCorrect(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if user is an admin.
     * @param username user's username
     * @return true if an admin.
     */
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

