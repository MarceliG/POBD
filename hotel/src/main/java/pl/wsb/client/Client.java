package pl.wsb.client;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    private String id;
    protected String firstName;
    protected String lastName;
    private LocalDate birthDate;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;
    private String idCardNumber;
    private Preference roomPreference;

    public Client(String id, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber,
            String email, String idCardNumber, Preference roomPreference) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCardNumber = idCardNumber;
        this.roomPreference = roomPreference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Preference getRoomPreference() {
        return roomPreference;
    }

    public void setRoomPreference(Preference roomPreference) {
        this.roomPreference = roomPreference;
    }


    public int getAge() {
        if (this.birthDate == null) {
            return 0;
        }
        LocalDate currentDate = LocalDate.now();
        this.age = Period.between(this.birthDate, currentDate).getYears();
        return this.age;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    @Override
    public String toString() {
        return "Client: " + id;
    }

    // Display all arguments for child and parent for testing
    public String getAllInformation() {
        StringBuilder sb = new StringBuilder();
        List<Field> fields = new ArrayList<>();

        Class<?> classItem = this.getClass();
        while (classItem != null) {
            fields.addAll(Arrays.asList(classItem.getDeclaredFields()));
            classItem = classItem.getSuperclass();
        }

        for (Field field : fields.toArray(new Field[0])) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                sb.append(field.getName()).append(": ").append(value).append("\n");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
