package org.example.hotel;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
    Integer id;
    Integer persons;
    Date beginDate;
    Date endDate;
    boolean premium;
    String workerResponsible;
    int reserved; //0 - not yet, -1 - rejected, 1 - reserved

    Reservation(int id, int persons, Date beginDate, Date endDate, boolean premium) {
        this.id = id;
        this.persons = persons;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.premium = premium;
        this.workerResponsible = null;
        this.reserved = 0;
    }

    Reservation() {}

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("persons", persons);
        json.put("premium", premium);
        json.put("workerResponsible", workerResponsible);
        json.put("reserved", reserved);
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        json.put("beginYear", cal.get(Calendar.YEAR));
        json.put("beginMonth", cal.get(Calendar.MONTH) + 1);  // Save as 1-12
        json.put("beginDay", cal.get(Calendar.DAY_OF_MONTH));
        cal.setTime(endDate);
        json.put("endYear", cal.get(Calendar.YEAR));
        json.put("endMonth", cal.get(Calendar.MONTH) + 1);  // Save as 1-12
        json.put("endDay", cal.get(Calendar.DAY_OF_MONTH));
        return json;
    }

    public static Reservation fromJSON(JSONObject json) {
        Reservation reservation = new Reservation();
        reservation.id = json.getInt("id");
        reservation.persons = json.getInt("persons");
        reservation.premium = json.getBoolean("premium");
        reservation.workerResponsible = json.optString("workerResponsible", null);
        reservation.reserved = json.getInt("reserved");
        int year = json.optInt("beginYear", 2024);
        int month = json.optInt("beginMonth", 1) - 1;  // Convert to 0-11
        int day = json.optInt("beginDay", 1);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        reservation.beginDate = cal.getTime();
        year = json.optInt("endYear", 2024);
        month = json.optInt("endMonth", 1) - 1;
        day = json.optInt("endDay", 1);
        cal.set(year, month, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        reservation.endDate = cal.getTime();
        return reservation;
    }
}
