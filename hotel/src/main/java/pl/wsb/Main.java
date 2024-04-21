package pl.wsb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("1", "Adam", "Smith", LocalDate.of(1723, 6, 5), "Edynburg 123 Street",
                "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING);
        System.out.println("Client: ");
        System.out.println(client.getAge());
        System.out.println(client.getFullName());
        System.out.println(client.getAllInformation());
        System.out.println("-----");

        PremiumClient clientPremium = new PremiumClient("2", "Adam", "Smitch", LocalDate.of(1723, 6, 5),
                "Edynburg 123 Street", "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING,
                PremiumAccountType.PREMIUM);
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
        System.out.println("-----");

        TimeService timeService = new TimeService("time_1");
        System.out.println(timeService.orderService());
        System.out.println(timeService.calculateCost(5));
        System.out.println(timeService.isAvailableOnDate(LocalDate.now()));
        System.out.println(timeService.getAllInformation());
        System.out.println("-----");

        LuggageService luggageService = new LuggageService("luggage_service");
        System.out.println(luggageService.name);
        System.out.println(luggageService.orderService());

        Hotel hotel_1 = new Hotel("hotel_1");
        System.out.println(hotel_1.getAllInformation());
        List<SpecialService> specialServices_1 = new ArrayList<>();
        specialServices_1.add(timeService);
        hotel_1.setSpecialServices(specialServices_1);
        System.out.println("-----");

        List<SpecialService> specialServices_2 = new ArrayList<>();
        specialServices_2.add(timeService);
        specialServices_2.add(luggageService);
        Hotel hotel_2 = new Hotel("hotel_2", specialServices_2, clientPremium, reservation, room);
        System.out.println(hotel_2.getAllInformation());
    }
}
