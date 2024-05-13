import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.wsb.Hotel;
import pl.wsb.exceptions.ClientNotFoundException;
import pl.wsb.exceptions.RoomNotFoundException;

public class HotelTest {
    final private Hotel hotel = new Hotel("Test hotel");

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
    void testAddNewReservation() throws RoomNotFoundException, ClientNotFoundException {
        // @TODO
    }

    @Test
    void testConfirmReservation() {
        // @TODO
    }

    @Test
    void testIsRoomReserved() {
        // @TODO
    }

    @Test
    void testGetRoomIdsReservedByClient() {
        // @TODO
    }

}
