package org.example.hotel;

public class Reservation {
    int id;
    int persons;
    String beginDate;
    String endDate;
    boolean premium;

    Reservation(int id, int persons, String beginDate, String endDate, boolean premium) {
        this.id = id;
        this.persons = persons;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.premium = premium;
    }
}
