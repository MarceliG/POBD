import java.time.LocalDate;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wsb.Hotel;
import pl.wsb.exceptions.ClientNotFoundException;
import pl.wsb.exceptions.RoomNotFoundException;
import pl.wsb.exceptions.RoomReservedException;

public class HotelTest {
    final private Hotel hotel = new Hotel("Test hotel");

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
        // Room ID intentionally not added

        Assertions.assertThrows(RoomNotFoundException.class, () -> {
            hotel.addNewReservation(clientId, "nonExistingRoomId", LocalDate.now());
        });
    }

    @Test
    void testAddNewReservationClientNotFound() throws RoomNotFoundException, RoomReservedException {
        String roomId = hotel.addRoom(1, 2, false, "Description");
        // Client ID intentionally not added

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            hotel.addNewReservation("nonExistingClientId", roomId, LocalDate.now());
        });
    }

    @Test
    void testAddNewReservationRoomReserved() throws ClientNotFoundException, RoomNotFoundException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");

        // Adding a reservation for the same room and date
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
    void testIsRoomReserved() throws RoomNotFoundException, ClientNotFoundException, RoomReservedException {
        String clientId = hotel.addClient("Firstname", "Lastname", LocalDate.now());
        String roomId = hotel.addRoom(1, 2, false, "Description");
        LocalDate date = LocalDate.now();
        hotel.addNewReservation(clientId, roomId, date);

        boolean isReserved = hotel.isRoomReserved(roomId, date);

        Assertions.assertTrue(isReserved);
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
