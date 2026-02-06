package org.example.hotel;

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

    public Hotel(String name, int stars, int floors, float defaultPremiumMultiplier, int defaultRoomsPerFloor, float baseRoomPrice) {
        this.name = name;
        this.stars = stars;
        this.floors = floors;
        this.defaultPremiumMultiplier = defaultPremiumMultiplier;
        this.defaultRoomsPerFloor = defaultRoomsPerFloor;
        this.baseRoomPrice = baseRoomPrice;
    }
}
