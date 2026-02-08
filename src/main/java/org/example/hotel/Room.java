package org.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Room {
    int id;
    String number;
    int floor;
    float price;
    boolean premium;
    int beds;

    public ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public Room(int id, String number, int floor, float price, boolean premium, int beds) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.price = price;
        this.premium = premium;
        this.beds = beds;
    }

    public Room(int id, String number, int floor, boolean premium, int beds) {
        this.id = id;
        this.number = number;
        this.floor = floor;
        this.premium = premium;
        this.beds = beds;
        this.price = 0;
    }
}
