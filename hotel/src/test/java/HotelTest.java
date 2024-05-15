import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wsb.Hotel;
import pl.wsb.client.Client;
import pl.wsb.exceptions.ClientNotFoundException;
import pl.wsb.exceptions.ReservationNotFoundException;
import pl.wsb.exceptions.RoomNotFoundException;
import pl.wsb.exceptions.RoomReservedException;
import pl.wsb.room.Room;
import pl.wsb.room.RoomReservation;
import pl.wsb.service.SpecialService;

public class HotelTest {
    final private Hotel hotel = new Hotel("Test hotel");

    @Test
    void testHotelConstructor() {
        String hotelName = "Test Hotel";
        List<SpecialService> specialServices = new ArrayList<>();
        Client client = new Client("1", "John", "Doe", LocalDate.of(1990, 5, 15), "", "", "", "", null);
        Room room = new Room("1", 20.0, 2, true, 1, true, true, true, true, "Description");
        RoomReservation reservation = new RoomReservation(LocalDate.of(2024, 5, 15), false, client, room);

        Hotel hotel = new Hotel(hotelName, specialServices, client, reservation, room);

        // Sprawdzamy, czy pola hotelu zostały poprawnie zainicjalizowane
        Assertions.assertEquals(hotelName, hotel.getName());
        Assertions.assertEquals(specialServices, hotel.getSpecialServices());
        Assertions.assertEquals(1, hotel.getClients().size());
        Assertions.assertEquals(client, hotel.getClients().get(0));
        Assertions.assertEquals(1, hotel.getReservations().size());
        Assertions.assertEquals(reservation, hotel.getReservations().get(0));
        Assertions.assertEquals(1, hotel.getRooms().size());
        Assertions.assertEquals(room, hotel.getRooms().get(0));
    }

    @Test
    void testGetRoomsIsEmpty() {
        Assertions.assertTrue(hotel.getRooms().isEmpty());
    }

    @Test
    void testAddClient() {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());

        Assertions.assertNotNull(clientId);
        Assertions.assertFalse(clientId.isEmpty());
    }

    @Test
    void testAddRoom() {
        String roomId = hotel.addRoom(1, 2, false, "Description");

        Assertions.assertNotNull(roomId);
        Assertions.assertFalse(roomId.isEmpty());
    }

    @Test
    void testGetRoomAreaForNonExistentRoom() {
        String nonExistentRoomId = UUID.randomUUID().toString();
        double area = hotel.getRoomArea(nonExistentRoomId);

        Assertions.assertTrue(Double.isNaN(area));
    }

    @Test
    void testAddNewReservation() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");
        LocalDate date = LocalDate.now();

        String reservationId = hotel.addNewReservation(clientId, roomId, date);

        Assertions.assertNotNull(reservationId);
        Assertions.assertFalse(reservationId.isEmpty());
    }

    @Test
    void testAddNewReservationRoomNotFound() throws ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());

        Assertions.assertThrows(RoomNotFoundException.class, () -> {
            hotel.addNewReservation(clientId, "nonExistingRoomId", LocalDate.now());
        });
    }

    @Test
    void testAddNewReservationClientNotFound() throws RoomNotFoundException, RoomReservedException {
        String roomId = hotel.addRoom(1, 2, false, "Description");

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            hotel.addNewReservation("nonExistingClientId", roomId, LocalDate.now());
        });
    }

    @Test
    void testAddNewReservationRoomReserved() throws ClientNotFoundException, RoomNotFoundException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");

        hotel.addNewReservation(clientId, roomId, LocalDate.now());

        Assertions.assertThrows(RoomReservedException.class, () -> {
            hotel.addNewReservation(clientId, roomId, LocalDate.now());
        });
    }

    @Test
    void testConfirmReservation() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");
        LocalDate date = LocalDate.now();
        String reservationId = hotel.addNewReservation(clientId, roomId, date);

        String confirmedReservationId = hotel.confirmReservation(reservationId);

        Assertions.assertEquals(reservationId, confirmedReservationId);
    }

    @Test
    void testConfirmReservationReservationNotFound() {
        Exception exception = Assertions.assertThrows(ReservationNotFoundException.class, () -> {
            hotel.confirmReservation("nonExistentReservationId");
        });

        String expectedMessage = "Reservation do not exists";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIsRoomReservedTrue() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");
        LocalDate date = LocalDate.now();
        hotel.addNewReservation(clientId, roomId, date);

        boolean isReserved = hotel.isRoomReserved(roomId, date);

        Assertions.assertTrue(isReserved);
    }


    @Test
    void testIsRoomReservedFalse() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String roomId = hotel.addRoom(1, 2, true, "Description");
        LocalDate date = LocalDate.now();

        boolean isReserved = hotel.isRoomReserved(roomId, date);

        Assertions.assertFalse(isReserved);
    }

    @Test
    void testIsRoomReservedNotFound() {
        LocalDate date = LocalDate.now();
        String nonExistentRoomId = UUID.randomUUID().toString();

        Exception exception = Assertions.assertThrows(RoomNotFoundException.class, () -> {
            hotel.isRoomReserved(nonExistentRoomId, date);
        });

        String expectedMessage = "Room not found: " + nonExistentRoomId;
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetRoomIdsReservedByClient() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");
        LocalDate date = LocalDate.now();
        hotel.addNewReservation(clientId, roomId, date);

        Collection<String> reservedRoomIds = hotel.getRoomIdsReservedByClient(clientId);

        Assertions.assertFalse(reservedRoomIds.isEmpty());
    }

    @Test
    void testGetNumberOfUnderageClients() {
        hotel.addClient("Jan", "Kowalski", LocalDate.of(2005, 5, 10));
        hotel.addClient("Anna", "Nowak", LocalDate.of(2023, 8, 20));

        int numberOfUnderageClients = hotel.getNumberOfUnderageClients();

        Assertions.assertEquals(1, numberOfUnderageClients);
    }

    @Test
    void testGetRoomArea() {
        String roomId = hotel.addRoom(20, 1, false, "Small Room");

        double roomArea = hotel.getRoomArea(roomId);

        Assertions.assertEquals(20, roomArea);
    }

    @Test
    void testGetNumberOfRoomsWithKingSizeBed() {
        hotel.addRoom(25, 1, true, "Room with King Size Bed");
        hotel.addRoom(25, 1, true, "Room with King Size Bed");
        hotel.addRoom(25, 1, true, "Room with King Size Bed");
        hotel.addRoom(35, 2, true, "Room with King Size Bed on 2nd floor");
        hotel.addRoom(40, 3, true, "Room with King Size Bed on 3rd floor");

        int numberOfRooms = hotel.getNumberOfRoomsWithKingSizeBed(1);

        Assertions.assertEquals(3, numberOfRooms);
    }
}
