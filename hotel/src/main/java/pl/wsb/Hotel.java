package pl.wsb;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import pl.wsb.client.Client;
import pl.wsb.client.Preference;
import pl.wsb.exceptions.ClientNotFoundException;
import pl.wsb.exceptions.ReservationNotFoundException;
import pl.wsb.exceptions.RoomNotFoundException;
import pl.wsb.exceptions.RoomReservedException;
import pl.wsb.interfaces.HotelCapability;
import pl.wsb.room.Room;
import pl.wsb.room.RoomReservation;
import pl.wsb.service.SpecialService;


public class Hotel implements HotelCapability {
    private String name;
    private List<SpecialService> specialServices;
    private List<Client> clients;
    private List<RoomReservation> reservations;
    private List<Room> rooms;

    public Hotel(String name, List<SpecialService> specialServices, Client client, RoomReservation reservation,
            Room room) {
        this.name = name;
        this.specialServices = new ArrayList<>(specialServices);
        this.clients = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.clients.add(client);
        this.reservations.add(reservation);
        this.rooms.add(room);
    }

    public Hotel(String name) {
        this.name = name;
        this.specialServices = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecialService> getSpecialServices() {
        return this.specialServices;
    }

    public void setSpecialServices(List<SpecialService> specialServices) {
        this.specialServices = specialServices;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<RoomReservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(List<RoomReservation> reservations) {
        this.reservations = reservations;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    // Display all arguments for testing
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

    public String addClient(String firstName, String lastName, LocalDate birthDate) {
        Client client = new Client(
            UUID.randomUUID().toString(),
            firstName,
            lastName,
            birthDate,
            "",
            "",
            "",
            "",
            null
        );
        this.clients.add(client);

        return client.getId();
    }

    public String getClientFullName(String clientId) {
        for(Client client : clients) {
            if(client.getId().equals(clientId)) {
                return client.getFullName();
            }
        }

        return null;
    }

    public int getNumberOfUnderageClients() {
        int count = 0;
        LocalDate currentDate = LocalDate.now();
        
        for(Client client: clients) {
            if(client.getBirthDate() != null) {
                int age = Period.between(client.getBirthDate(), currentDate).getYears();
                if(age < 18) {
                    count++;
                } 
            }
        }

        return count;
    }

    public String addRoom(double area, int floor, boolean hasKingSizeBed, String description) {
        Room room = new Room(
            UUID.randomUUID().toString(),
            area,
            floor,
            hasKingSizeBed,
            1,
            true,
            true,
            true,
            true,
            description
        );

        this.rooms.add(room);

        return room.getId();
    }

    public double getRoomArea(String roomId) {
        for(Room room: rooms) {
            if(room.getId().equals(roomId)) {
                room.getArea();
            }
        }

        return -1;
    }
    
    public int getNumberOfRoomsWithKingSizeBed(int floor) {
        int count = 0;

        for(Room room: rooms) {
            if(room.getHasKingSizeBed()) {
                count++;
            }
        }

        return count;
    }

    public String addNewReservation(String clientId, String roomId, LocalDate date) throws RoomNotFoundException, ClientNotFoundException {
        Client searchClient = null;
        for(Client client: clients) {
            if(client.getId().equals(clientId)) {
                searchClient = client;
            }
        }

        if(searchClient == null) {
            throw new ClientNotFoundException("Client not found: " + clientId);
        }

        Room searchRoom = null;
        for(Room room: rooms) {
            if(room.getId().equals(roomId)) {
                searchRoom = room;
            }
        }

        if(searchRoom == null) {
            throw new RoomNotFoundException("Room not found: " + roomId);
        }

        for(RoomReservation reservation: reservations) {
            if(reservation.getDate().equals(date)) {
                throw new RoomReservedException("This date is reserved", date);
            }
        }        

        RoomReservation reservation = new RoomReservation(date, false, searchClient, searchRoom);
        this.reservations.add(reservation);

        return reservation.getId();
    }

    public String confirmReservation(String reservationId) {
        RoomReservation searchReservation = null;
        for(RoomReservation reservation: reservations) {
            if(reservation.getId().equals(reservationId)) {
                searchReservation = reservation;
            }
        }

        if(searchReservation == null) {
            throw new ReservationNotFoundException("Reservation do not exists");
        }

        return searchReservation.getId();
    }

    public boolean isRoomReserved(String roomId, LocalDate date) throws RoomNotFoundException {
        Room room = null;

        for(RoomReservation reservation: reservations) {
            room = reservation.getRoom();

            if(room.getId().equals(roomId) && reservation.getDate().equals(date)) {
                return true;
            }
        }

        if(room == null) {
            throw new RoomNotFoundException("Room not found: " + room);
        }

        return false;
    }

    public int getNumberOfUnconfirmedReservation(LocalDate date) {
        // @TODO
    }

    public Collection<String> getRoomIdsReservedByClient(String clientId) {
        // @TODO
    }

}
