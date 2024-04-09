package pl.wsb;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("1", "Adam", "Smith", LocalDate.of(1723, 6, 5), "Edynburg 123 Street",
                "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING);
        System.out.println("Client: ");
        System.out.println(client.getAge());
        System.out.println(client.getFullName());
        System.out.println(client.getAllInformation());
        System.out.println("-----");

        PremiumClient clientPremium = new PremiumClient("2", "Adam", "Smitch",LocalDate.of(1723, 6, 5), "Edynburg 123 Street",
                "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING, PremiumAccountType.PREMIUM);
        System.out.println("Client Premium: ");
        System.out.println(clientPremium.getAge());
        System.out.println(clientPremium.getFullName());
        System.out.println(clientPremium.getAllInformation());
        System.out.println("-----");

        Room room = new Room("1", 8.0, 1, false, 2, true, true, true, true);
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
