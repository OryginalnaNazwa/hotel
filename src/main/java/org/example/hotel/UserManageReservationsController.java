package org.example.hotel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class UserManageReservationsController {
    @FXML
    private Label idValue;
    @FXML
    private Label personsValue;
    @FXML
    private Label premiumValue;
    @FXML
    private Label beginDateValue;
    @FXML
    private Label endDateValue;
    @FXML
    private Label floorValue;
    @FXML
    private HBox roomsMap;
    @FXML
    private Label roomInfo;

    Integer currentFloor = 1;
    public String currentlyLoggedUser = "";
    Database database = new Database();
    Reservation currentReservation = null;
    Room selectedRoom = null;

    @FXML
    void initialize() {
        floorValue.setText(currentFloor.toString());
        MapRawFloor();
    }

    /**
     * Loads a new reservation.
     */
    public void GetReservations() {
        for (Reservation reservation : database.reservations) {
            if (Objects.equals(reservation.workerResponsible, currentlyLoggedUser) && reservation.reserved == 0) {
                currentReservation = reservation;
                DoReservation();
                return;
            }
        }
        CleanUp();

    }

    /**
     * Resets the view to default.
     */
    private void CleanUp() {
        idValue.setText("-");
        personsValue.setText("-");
        premiumValue.setText("-");
        beginDateValue.setText("-");
        endDateValue.setText("-");
        currentReservation = null;
        MapFloor();
    }

    private void DoReservation() {
        MapFloor();
        idValue.setText(currentReservation.id.toString());
        personsValue.setText(currentReservation.persons.toString());
        if (currentReservation.premium) {
            premiumValue.setText("Yes");
        } else  {
            premiumValue.setText("No");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beginDateValue.setText(dateFormat.format(currentReservation.beginDate));
        endDateValue.setText(dateFormat.format(currentReservation.endDate));
    }

    /**
     * Creates the floor map.
     * Calls for a different function depending on current reservation state.
     */
    private void MapFloor() {
        if (currentReservation != null) {
            MapReservationFloor();
        } else MapRawFloor();
    }

    /**
     * Creates the floor map, filling the rooms with colours according to their eligibility for reservation.
     */
    private void MapReservationFloor() {
        roomsMap.getChildren().clear();
        floorValue.setText(currentFloor.toString());
        for (Room room : database.hotel.rooms) {
            if (room.floor == currentFloor) {
                Rectangle aRoom = new Rectangle(40,40);
                switch (CheckRoom(room)) {
                    case -1:
                        aRoom.setFill(Color.RED);
                        break;
                    case 0:
                        aRoom.setFill(Color.YELLOW);
                        break;
                        case 1:
                        aRoom.setFill(Color.GREEN);
                        break;
                }
                final Room clickedRoom = room; // Need final for lambda
                aRoom.setOnMouseClicked(event -> {
                    selectedRoom = clickedRoom;
                    showRoomInfo(clickedRoom);
                    MapFloor();
                });
                aRoom.setStroke(Color.BLACK);
                if (selectedRoom != null && room.id == selectedRoom.id) {
                    aRoom.setStrokeWidth(5);
                } else {
                    aRoom.setStrokeWidth(2);
                }

                roomsMap.getChildren().add(aRoom);
            }
        }
    }

    /**
     * Checks if the reservation date collides with any other reservations for the room
     * @param room room being checked
     * @return true if room is available, false otherwise.
     */
    private boolean ReservationDateCheck(Room room) {
        for (Reservation reservation : room.reservations) {
            if (reservation.id != currentReservation.id) {
                // Check if dates overlap
                // Overlap occurs if: new start < existing end AND new end > existing start
                if (currentReservation.beginDate.compareTo(reservation.endDate) < 0 &&
                        currentReservation.endDate.compareTo(reservation.beginDate) > 0) {
                    return false; // Dates overlap - room not available
                }
            }
        }
        return true; // No overlaps - room is available
    }

    /**
     * Checks room's state when compared to the reservation specifications.
     * @param room Room being checked.
     * @return -1 if room is not up to specifications, 0 if passable, 1 if good.
     */
    private int RoomState(Room room) {
        if (room.beds < currentReservation.persons) {
            return -1;
        }
        if (room.beds == currentReservation.persons) {
            if (room.premium == currentReservation.premium) {
                return 1;
            } else return 0;
        } else return 0;
    }

    /**
     * Checks if room is able to be reserved for the current reservation.
     * @param room currently checked room.
     * @return -1 if not, 0 if can be in theory, 1 if should be.
     */
    private int CheckRoom(Room room) {
        if (!ReservationDateCheck(room)) {
            return -1;
        }
        return RoomState(room);
    }

    /**
     * Creates an empty room map.
     */
    private void MapRawFloor() {
        roomsMap.getChildren().clear();
        floorValue.setText(currentFloor.toString());
        for (Room room : database.hotel.rooms) {
            if (room.floor == currentFloor) {
                Rectangle aRoom = new Rectangle(40,40);
                aRoom.setFill(Color.WHITE);
                aRoom.setStroke(Color.BLACK);
                aRoom.setStrokeWidth(2);

                roomsMap.getChildren().add(aRoom);
            }
        }
    }

    /**
     * Increments current floor.
     * Checks for overflow.
     */
    @FXML
    void IncreaseFloor() {
        if (currentFloor < database.hotel.floors) {
            currentFloor++;
        }
        MapFloor();
    }

    /**
     * Decrements current floor.
     * Checks for underflow.
     */
    @FXML
    void DecreaseFloor() {
        if (currentFloor > 0) {
            currentFloor--;
        }
        MapFloor();
    }

    /**
     * Saves the database and closes the window.
     */
    @FXML
    void onFinishClick() {
        FileOperations.saveUsers(database.users);
        FileOperations.saveHotel(database.hotel);
        FileOperations.saveRooms(database.hotel.rooms);
        FileOperations.saveReservations(database.reservations);
        Stage stage_this = (Stage) personsValue.getScene().getWindow();
        stage_this.close();
    }

    @FXML
    void onRejectClick() {
        if (currentReservation != null) {
            currentReservation.reserved = -1;
            for (Reservation reservation : database.reservations) {
                if (reservation.id == currentReservation.id) {
                    reservation.reserved = currentReservation.reserved;
                }
            }
            FileOperations.saveReservations(database.reservations);
            GetReservations();
        }
    }

    @FXML
    void onAcceptClick() {
        if (currentReservation != null && selectedRoom != null) {
            if (CheckRoom(selectedRoom) == -1) {
                return;
            }
            currentReservation.reserved = 1;
            for (Reservation reservation : database.reservations) {
                if (reservation.id == currentReservation.id) {
                    reservation.reserved = currentReservation.reserved;
                }
            }
            selectedRoom.reservations.add(currentReservation);
            FileOperations.saveReservations(database.reservations);
            FileOperations.saveRooms(database.hotel.rooms);
            GetReservations();
        }
    }

    private void showRoomInfo(Room room) {
        int roomCheck = CheckRoom(room);
        String info = "Room " + room.number + " - Floor " + room.floor + "\n";
        info += "Beds: " + room.beds + "\n";
        info += "Premium: " + (room.premium ? "Yes" : "No") + "\n";

        switch (roomCheck) {
            case -1:
                info += "Status: NOT SUITABLE\n";
                if (room.beds < currentReservation.persons) {
                    info += "Reason: Not enough beds";
                } else {
                    info += "Reason: Already booked for these dates";
                }
                break;
            case 0:
                info += "Status: ACCEPTABLE (not ideal match)";
                break;
            case 1:
                info += "Status: PERFECT MATCH";
                break;
        }

        roomInfo.setText(info);
    }
}
