package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @class Database
 * A simple representation of a database through text files.
 */
public class Database {
    public FileOperations fileOp = new FileOperations();
    public ObservableList<User> users = FXCollections.observableArrayList();
    public Hotel hotel = new Hotel();
    public ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public Database() {
        users = FileOperations.loadUsers();
        hotel = FileOperations.loadHotel();
        assert hotel != null;
        hotel.rooms = FileOperations.loadRooms();
        hotel.UpdateRoomPrices();
        reservations = FileOperations.loadReservations();
        AssignReservations();
        FileOperations.saveReservations(reservations);

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

    /**
     * Gets the Name of the user.
     * @param username which user's Name it will get.
     * @return Name if user exists, empty string otherwise.
     */
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

    /**
     * Assigns reservations to workers (non-admin users).
     */
    private void AssignReservations() {
        Random rand = new Random();
        List<User> workers = new ArrayList<>();
        for (User user : users) {
            if (!IsUserAdmin(user.username)) {
                workers.add(user);
            }
        }
        if (workers.isEmpty()) {
            return;
        }

        for (Reservation reservation : reservations) {
            if (reservation.workerResponsible == null) {
                int randomIndex = rand.nextInt(workers.size());
                reservation.workerResponsible = workers.get(randomIndex).username;
            }
        }
    }


}

