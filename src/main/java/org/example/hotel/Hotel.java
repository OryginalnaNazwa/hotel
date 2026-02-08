package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void AddRoom(int floor, String number, boolean premium, int beds) {
        rooms.add(new Room(rooms.size() + 1, number, floor, premium, beds));
    }

    public void AddRoom(int floor, String number, float price, boolean premium, int beds) {
        rooms.add(new Room(rooms.size() + 1, number, floor, price, premium, beds));
    }

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
}
