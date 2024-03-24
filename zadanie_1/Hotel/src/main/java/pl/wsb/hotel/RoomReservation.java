package pl.wsb.hotel;

import java.time.LocalDate;

public class RoomReservation {
    private LocalDate date;
    private boolean isConfirmed;
    private Client client;
    private Room room;

    public RoomReservation(LocalDate date, boolean isConfirmed, Client client, Room room) {
        this.date = date;
        this.isConfirmed = false;
        this.client = client;
        this.room = room;
    }

    public void confirmReservation() {
        if (!this.isConfirmed){
            this.isConfirmed = true;
        }
    }

    public String getAllInformation() {
        return String.format(
            "Room information:\n%s\n\nClient information:\n%s\n\nReservation dates: %s\nis confirmed: %s",
            room.getAllInformation(),
            client.getAllInformation(),
            this.date,
            this.isConfirmed
        );
    }
}
