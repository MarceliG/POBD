package pl.wsb.room;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.UUID;
import pl.wsb.client.Client;

public class RoomReservation {
    private String id;
    private LocalDate date;
    private boolean isConfirmed;
    private Client client;
    private Room room;

    public RoomReservation(String id, LocalDate date, boolean isConfirmed, Client client, Room room) {
        this.id = id;
        this.date = date;
        this.isConfirmed = false;
        this.client = client;
        this.room = room;
    }

    public RoomReservation(LocalDate date, boolean isConfirmed, Client client, Room room) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.isConfirmed = false;
        this.client = client;
        this.room = room;
    }

    public void confirmReservation() {
        if (!this.isConfirmed) {
            this.isConfirmed = true;
        }
    }

    public boolean isConfirmed() {
        return this.isConfirmed;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return this.room;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", " + room;
    }

    public String getAllInformation() {
        StringBuilder sb = new StringBuilder();
        Class<?> thisClass = this.getClass();
        Field[] fields = thisClass.getDeclaredFields();

        for (Field field : fields) {
            try {
                Object value = field.get(this);
                sb.append(field.getName()).append(": ").append(value).append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
