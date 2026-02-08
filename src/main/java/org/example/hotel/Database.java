package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Database {
    public ObservableList<User> users = FXCollections.observableArrayList();
    public Hotel hotel = new Hotel("HotelX", 3, 2, 2, 2, 1);

    public Database() {
        users.add(new User("admin", "admin", true));
        users.add(new User("user", "user", false));

        hotel.AddRoom(1,"1", 1000.0F,false,3);
        hotel.AddRoom(1,"2", 1000.0F,false,3);
        hotel.AddRoom(1,"3", 0.0F,false,3);
        hotel.AddRoom(1,"4", 0.0F,false,2);
        hotel.AddRoom(2,"1", 2000.0F,true,1);
        hotel.AddRoom(2,"1", 0.0F,true,2);
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

    public String GetUserName(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.name;
            }
        }
        return "";
    }

    public String GetUserLastName(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.lastName;
            }
        }
        return "";
    }

    public String GetUserEmail(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.email;
            }
        }
        return "";
    }

    public String GetUserPhone(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.phone;
            }
        }
        return "";
    }

    public String GetUserPassword(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.password;
            }
        }
        return "";
    }

    public void SetUserName(String username, String name) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.name = name;
            }
        }
    }

    public void SetUserLastName(String username, String lastName) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.lastName = lastName;
            }
        }
    }

    public void SetUserEmail(String username, String email) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.email = email;
            }
        }
    }

    public void SetUserPhone(String username, String phone) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.phone = phone;
            }
        }
    }

    public void SetUserPassword(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username)) {
                user.password = password;
            }
        }
    }
}

