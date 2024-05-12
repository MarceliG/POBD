package pl.wsb.room;

import java.lang.reflect.Field;

public class Room {
    private String id;
    private double area;
    private int floor;
    private boolean hasKingSizeBed;
    private int countBed;
    private boolean hasToilet;
    private boolean hasBalcony;
    private boolean hasMinibar;
    private boolean hasTV;
    private String description;


    public Room(String id, double area, int floor, boolean hasKingSizeBed, int countBed, boolean hasToilet,
            boolean hasBalcony, boolean hasMinibar, boolean hasTV, String description) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.countBed = countBed;
        this.hasToilet = hasToilet;
        this.hasBalcony = hasBalcony;
        this.hasMinibar = hasMinibar;
        this.hasTV = hasTV;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setHasKingSizeBed(boolean hasKingSizeBed) {
        this.hasKingSizeBed = hasKingSizeBed;
    }

    public boolean getHasKingSizeBed() {
        return this.hasKingSizeBed;
    }

    public void setCountBed(int countBed) {
        this.countBed = countBed;
    }

    public int getCountBed() {
        return this.countBed;
    }

    public void setHasToilet(boolean hasToilet) {
        this.hasToilet = hasToilet;
    }

    public boolean getHasToilet() {
        return this.hasToilet;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean hasBalcony() {
        return this.hasBalcony;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public boolean getHasMinibar() {
        return this.hasMinibar;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean getHasTV() {
        return this.hasTV;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "Room number: " + id;
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
}
