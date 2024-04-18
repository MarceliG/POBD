package pl.wsb;

import java.util.ArrayList;

public class Hotel {
    private String name;
    private ArrayList<SpecialService> specialServices;
    private ArrayList<Client> clients;
    private ArrayList<RoomReservation> reservations;
    private ArrayList<Room> rooms;

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

    public ArrayList<SpecialService> getSpecialServices() {
        return this.specialServices;
    }

    public void setSpecialServices(ArrayList<SpecialService> specialServices) {
        this.specialServices = specialServices;
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<RoomReservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(ArrayList<RoomReservation> reservations) {
        this.reservations = reservations;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }


}
