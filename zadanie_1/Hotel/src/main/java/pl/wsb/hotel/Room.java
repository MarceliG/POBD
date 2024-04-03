package pl.wsb.hotel;

import java.lang.reflect.Field;

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
