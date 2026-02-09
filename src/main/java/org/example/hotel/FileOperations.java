package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import static org.example.hotel.User.*;

/**
 * Utility class for I/O operations.
 */
public class FileOperations {

    // Save a single object
    public static void saveToFile(JSONObject jsonObject, String filename) {
        try {
            Files.writeString(Paths.get(filename), jsonObject.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save an array of objects
    public static void saveToFile(JSONArray jsonArray, String filename) {
        try {
            Files.writeString(Paths.get(filename), jsonArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load and return JSONObject
    public static JSONObject loadObject(String filename) {
        try {
            String content = Files.readString(Paths.get(filename));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Load and return JSONArray
    public static JSONArray loadArray(String filename) {
        try {
            String content = Files.readString(Paths.get(filename));
            return new JSONArray(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Loading from JSONArray to ObservableList
    public static ObservableList<Room> loadRooms() {
        String filename = "rooms.json";
        ObservableList<Room> rooms = FXCollections.observableArrayList();

        JSONArray jsonArray = FileOperations.loadArray(filename);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRoom = jsonArray.getJSONObject(i);
                Room room = Room.fromJSON(jsonRoom);
                rooms.add(room);
            }
        }

        return rooms;
    }

    // Saving from ObservableList to JSONArray
    public static void saveRooms(ObservableList<Room> rooms) {
        String filename = "rooms.json";
        JSONArray jsonArray = new JSONArray();

        for (Room room : rooms) {
            jsonArray.put(room.toJSON());
        }

        FileOperations.saveToFile(jsonArray, filename);
    }

    public static Hotel loadHotel() {
        String filename = "hotel.json";
        JSONObject jsonObject = FileOperations.loadObject(filename);
        if (jsonObject != null) {
            return Hotel.fromJSON(jsonObject);
        }
        return null;
    }

    // Save single object
    public static void saveHotel(Hotel hotel) {
        FileOperations.saveToFile(hotel.toJSON(), "hotel.json");
    }

    // Loading from JSONArray to ObservableList
    public static ObservableList<User> loadUsers() {
        String filename = "users.json";
        ObservableList<User> users = FXCollections.observableArrayList();

        JSONArray jsonArray = FileOperations.loadArray(filename);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonUser = jsonArray.getJSONObject(i);
                User user = User.fromJSON(jsonUser);
                users.add(user);
            }
        }

        return users;
    }

    // Saving from ObservableList to JSONArray
    public static void saveUsers(ObservableList<User> users) {
        String filename = "users.json";
        JSONArray jsonArray = new JSONArray();

        for (User user : users) {
            jsonArray.put(user.toJSON());
        }

        FileOperations.saveToFile(jsonArray, filename);
    }

    // Loading from JSONArray to ObservableList
    public static ObservableList<Reservation> loadReservations() {
        String filename = "reservations.json";
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();

        JSONArray jsonArray = FileOperations.loadArray(filename);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonReservation = jsonArray.getJSONObject(i);
                Reservation reservation = Reservation.fromJSON(jsonReservation);
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    // Saving from ObservableList to JSONArray
    public static void saveReservations(ObservableList<Reservation> reservations) {
        String filename = "reservations.json";
        JSONArray jsonArray = new JSONArray();

        for (Reservation reservation : reservations) {
            jsonArray.put(reservation.toJSON());
        }

        FileOperations.saveToFile(jsonArray, filename);
    }

    public static void saveConfig() {
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("language", Language.getCurrentLanguage());
        FileOperations.saveToFile(jsonConfig, "config.json");
    }

    public static void loadConfig() {
        String filename = "config.json";
        JSONObject jsonObject = FileOperations.loadObject(filename);
        if (jsonObject != null) {
            Language.loadLanguage(jsonObject.optString("language", "en"));
        }
    }
}
