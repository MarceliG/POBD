package pl.wsb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pl.wsb.client.Client;
import pl.wsb.client.Preference;
import pl.wsb.client.PremiumAccountType;
import pl.wsb.client.PremiumClient;
import pl.wsb.room.Room;
import pl.wsb.room.RoomReservation;
import pl.wsb.service.LuggageService;
import pl.wsb.service.SpecialService;
import pl.wsb.service.TimeService;

public class Main {
    public static void main(String[] args) {

        Client client = new Client("1", "Adam", "Smith", LocalDate.of(1723, 6, 5), "Edynburg 123 Street",
                "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING);
        System.out.println("Client: ");
        System.out.println(client.getId());
        System.out.println(client.getFirstName());
        System.out.println(client.getLastName());
        System.out.println(client.getFullName());
        System.out.println(client.getBirthDate());
        System.out.println(client.getAge());
        System.out.println(client.getAddress());
        System.out.println(client.getPhoneNumber());
        System.out.println(client.getEmail());
        System.out.println(client.getIdCardNumber());
        System.out.println(client.getRoomPreference());
        System.out.println("-----");

        PremiumClient clientPremium = new PremiumClient("2", "Adam", "Smitch", LocalDate.of(1723, 6, 5),
                "Edynburg 123 Street", "123-456-7890", "adam.smith@example.com", "ABCD1234", Preference.NONSMOKING,
                PremiumAccountType.PREMIUM);
        System.out.println("Client Premium: ");
        System.out.println(client.getId());
        System.out.println(client.getFirstName());
        System.out.println(client.getLastName());
        System.out.println(client.getFullName());
        System.out.println(client.getBirthDate());
        System.out.println(client.getAge());
        System.out.println(client.getAddress());
        System.out.println(client.getPhoneNumber());
        System.out.println(client.getEmail());
        System.out.println(client.getIdCardNumber());
        System.out.println(client.getRoomPreference());
        System.out.println("-----");

        Room room = new Room("1", 8.0, 1, false, 2, true, true, true, true, "");
        System.out.println("Room: ");
        System.out.println(room.getId());
        System.out.println(room.getArea());
        System.out.println(room.getFloor());
        System.out.println(room.getHasKingSizeBed());
        System.out.println(room.getCountBed());
        System.out.println(room.getHasToilet());
        System.out.println(room.getHasBalcony());
        System.out.println(room.getHasMinibar());
        System.out.println(room.getHasTV());
        System.out.println(room.getDescription());
        System.out.println("-----");

        RoomReservation reservation = new RoomReservation(LocalDate.now(), false, client, room);
        System.out.println("RoomReservation: ");
        reservation.confirmReservation();
        System.out.println(reservation.getId());
        System.out.println(reservation.getDate());
        System.out.println(reservation.isConfirmed());
        System.out.println(reservation.getClient());
        System.out.println(reservation.getRoom());
        System.out.println("-----");

        TimeService timeService = new TimeService("time_1");
        System.out.println("TimeService: ");
        System.out.println(timeService.orderService());
        System.out.println(timeService.calculateCost(5));
        System.out.println(timeService.isAvailableOnDate(LocalDate.now()));
        System.out.println("-----");

        LuggageService luggageService = new LuggageService("luggage_service");
        System.out.println("LuggageService: ");
        System.out.println(luggageService.orderService());
        System.out.println("-----");

        Hotel hotel_1 = new Hotel("hotel_1");
        List<SpecialService> specialServices_1 = new ArrayList<>();
        specialServices_1.add(timeService);
        hotel_1.setSpecialServices(specialServices_1);
        System.out.println("Hotel 1: ");
        System.out.println(hotel_1.getName());
        System.out.println(hotel_1.getSpecialServices());
        System.out.println("-----");

        List<SpecialService> specialServices_2 = new ArrayList<>();
        specialServices_2.add(timeService);
        specialServices_2.add(luggageService);
        Hotel hotel_2 = new Hotel("hotel_2", specialServices_2, clientPremium, reservation, room);
        System.out.println("Hotel 2: ");
        System.out.println(hotel_2.getName());
        System.out.println(hotel_2.getSpecialServices());
        System.out.println(hotel_2.getClients());
        System.out.println(hotel_2.getReservations());
        System.out.println(hotel_2.getRooms());
        System.out.println("-----");
    }
}
