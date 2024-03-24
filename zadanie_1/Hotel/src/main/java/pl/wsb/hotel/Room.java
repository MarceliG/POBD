package pl.wsb.hotel;

public class Room {
    String id;
    double area;
    int floor;
    boolean hasKingSizeBed;

    public Room(String id, double area, int floor, boolean hasKingSizeBed) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
    }

    // Display all arguments for testing
    public String getAllInformation() {
        return String.format(
            "id: %s\narea: %s\nfloor: %s\nhas king size bed: %s",
            this.id,
            this.area,
            this.floor,
            this.hasKingSizeBed
        );
    }
}
