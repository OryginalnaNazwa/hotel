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

    public void GetReservations() {
        personsValue.setText(currentlyLoggedUser);
        for (Reservation reservation : database.reservations) {
            if (Objects.equals(reservation.workerResponsible, currentlyLoggedUser)) {
                currentReservation = reservation;
                DoReservation();
                break;
            }
        }
    }
    private void DoReservation() {
        MapFloor();
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
                    onRoomClick(clickedRoom);
                });
                aRoom.setStroke(Color.BLACK);
                aRoom.setStrokeWidth(2);

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
                if (reservation.endDate.compareTo(currentReservation.beginDate) > 0 && reservation.beginDate.compareTo(currentReservation.beginDate) > 0) {
                    return false;
                }
            }
        }
        return true;
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
        if (currentReservation != null) {
            currentReservation.reserved = 1;
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
    void onRoomClick(Room room) {

    }
}
