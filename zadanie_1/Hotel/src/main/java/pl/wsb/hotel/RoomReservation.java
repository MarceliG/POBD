package pl.wsb.hotel;

import java.lang.reflect.Field;
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
        if (!this.isConfirmed) {
            this.isConfirmed = true;
        }
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
