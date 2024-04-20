package pl.wsb;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<SpecialService> specialServices;
    private List<Client> clients;
    private List<RoomReservation> reservations;
    private List<Room> rooms;

    public Hotel(String name, SpecialService specialService, Client client, RoomReservation reservation, Room room) {
        this.name = name;
        this.specialServices.add(specialService);
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


}
