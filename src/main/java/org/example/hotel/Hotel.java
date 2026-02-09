package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;

/**
 * @class Hotel
 * A class holding the basic information about the hotel.
 */
public class Hotel {
    String name;
    String address;
    String city;
    String country;
    String phone;
    String email;
    int stars;
    int floors;
    int defaultRoomsPerFloor;
    Float defaultPremiumMultiplier;
    float baseRoomPrice;

    public ObservableList<Room> rooms = FXCollections.observableArrayList();

    public Hotel(String name, int stars, int floors, float defaultPremiumMultiplier, int defaultRoomsPerFloor, float baseRoomPrice) {
        this.name = name;
        this.stars = stars;
        this.floors = floors;
        this.defaultPremiumMultiplier = defaultPremiumMultiplier;
        this.defaultRoomsPerFloor = defaultRoomsPerFloor;
        this.baseRoomPrice = baseRoomPrice;
    }

    public Hotel() {}

    /**
     * Adds a room - defaults price.
     * Price is 0, requires UpdateRoomPrices to default it correctly.
     * @param floor which floor the room is in.
     * @param number room's number on the floor.
     * @param premium is the room of higher standard.
     * @param beds how many beds are there/how many people can sleep there.
     */
    public void AddRoom(int floor, String number, boolean premium, int beds) {
        rooms.add(new Room(rooms.size() + 1, number, floor, premium, beds));
    }

    /**
     * Adds a room.
     * @param floor which floor the room is in.
     * @param number room's number on the floor.
     * @param price room's price
     * @param premium is the room of higher standard.
     * @param beds how many beds are there/how many people can sleep there.
     */
    public void AddRoom(int floor, String number, float price, boolean premium, int beds) {
        rooms.add(new Room(rooms.size() + 1, number, floor, price, premium, beds));
    }

    /**
     * Updates non-set room prices to the default price;
     * Default price: base price of a room times number of beds, then multiplied by premium multiplier if applicable.
     */
    public void UpdateRoomPrices() {
        for (Room room : rooms) {
            if (room.price == 0) {
                room.price = baseRoomPrice * room.beds;
                if (room.premium) {
                    room.price *= defaultPremiumMultiplier;
                }
            }
        }
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("stars", stars);
        json.put("floors", floors);
        json.put("address", address);
        json.put("city", city);
        json.put("country", country);
        json.put("phone", phone);
        json.put("email", email);
        json.put("defaultRoomsPerFloor", defaultRoomsPerFloor);
        json.put("defaultPremiumMultiplier", defaultPremiumMultiplier);
        json.put("baseRoomPrice", baseRoomPrice);
        return json;
    }

    // Convert FROM JSON
    public static Hotel fromJSON(JSONObject json) {
        Hotel obj = new Hotel();
        obj.name = json.getString("name");
        obj.stars = json.getInt("stars");
        obj.floors = json.getInt("floors");
        obj.address = json.optString("address", "No address");
        obj.city = json.optString("city", "No city");
        obj.country = json.optString("country", "No country");
        obj.phone = json.optString("phone", "No phone");
        obj.email = json.optString("email", "No email");
        obj.defaultRoomsPerFloor = json.getInt("defaultRoomsPerFloor");
        obj.baseRoomPrice = json.getFloat("baseRoomPrice");
        obj.defaultPremiumMultiplier = json.getFloat("defaultPremiumMultiplier");
        return obj;
    }
}
