package main.java;

public class Room {
    private String id;
    private String description;
    private double area;
    private int floor;
    private boolean hasKingSizeBed;

    public Room(String id, String description, double area, int floor, boolean hasKingSizeBed) {
        this.id = id;
        this.description = description;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
    }

    // Display all arguments for testing
    public String getAllInformation() {
        return String.format(
            "id: %s\ndescription: %s\narea: %s\nfloor: %s\nhas king size bed: %s",
            this.id,
            this.description,
            this.area,
            this.floor,
            this.hasKingSizeBed
        );
    }
}
