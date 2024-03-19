import java.time.LocalDate;

import main.java.Client;
import main.java.Room;
import main.java.RoomReservation;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("1", "Adam", "Smith", LocalDate.of(2000, 1, 1));
        System.out.println("Client: ");
        System.out.println(client.getAge());
        System.out.println(client.getFullName());
        System.out.println(client.getAllInformation());
        System.out.println("-----");
        
        Room room = new Room("1", "description", 8.0, 1, false);
        System.out.println("Room: ");
        System.out.println(room.getAllInformation());
        System.out.println("-----");

        RoomReservation reservation = new RoomReservation(LocalDate.now(), false, client, room);
        System.out.println("RoomReservation: ");
        System.out.println(reservation.getAllInformation());
        reservation.confirmReservation();
        System.out.println("-----");
        System.out.println(reservation.getAllInformation());
    }
}
