package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Room {
    int id;
    String number;
    int floor;
    float price;
    boolean premium;
    int beds;

    public ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    /**
     * Constructor with price.
     * @param id Room's id.
     * @param number Room's number.
     * @param floor The floor on which the room is located.
     * @param price Price of the room.
     * @param premium Is the room of a higher standard.
     * @param beds How many beds are inside/how many people can sleep there.
     */
    public Room(int id, String number, int floor, float price, boolean premium, int beds) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.price = price;
        this.premium = premium;
        this.beds = beds;
    }

    /**
     * Constructor with a defaulting price.
     * See Hotel for how prices are defaulted.
     * @param id Room's id.
     * @param number Room's number.
     * @param floor The floor on which the room is located.
     * @param premium Is the room of a higher standard.
     * @param beds How many beds are inside/how many people can sleep there.
     */
    public Room(int id, String number, int floor, boolean premium, int beds) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.premium = premium;
        this.beds = beds;
        this.price = 0;
    }

    public Room() {}

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("number", number);
        json.put("floor", floor);
        json.put("premium", premium);
        json.put("beds", beds);
        json.put("price", price);

        JSONArray reservationsArray = new JSONArray();
        for (Reservation res : reservations) {
            reservationsArray.put(res.toJSON());
        }
        json.put("reservations", reservationsArray);
        return json;
    }

    public static Room fromJSON(JSONObject json) {
        Room room = new Room();
        room.id = json.getInt("id");
        room.number = json.getString("number");
        room.floor = json.getInt("floor");
        room.premium = json.getBoolean("premium");
        room.beds = json.getInt("beds");
        room.price = json.optFloat("price", 0);

        JSONArray reservationsArray = json.optJSONArray("reservations");
        if (reservationsArray != null) {
            for (int i = 0; i < reservationsArray.length(); i++) {
                JSONObject resJson = reservationsArray.getJSONObject(i);
                Reservation res = Reservation.fromJSON(resJson);
                room.reservations.add(res);
            }
        }
        return room;
    }
}
