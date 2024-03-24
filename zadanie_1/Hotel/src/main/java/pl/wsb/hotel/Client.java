package pl.wsb.hotel;

import java.time.LocalDate;
import java.time.Period;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Client(String id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getAge() {
        if (this.birthDate == null) {
            return 0;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(this.birthDate, currentDate).getYears();
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    // Display all arguments for testing
    public String getAllInformation() {
        return String.format(
                "id: %s\nfirst name: %s\nlast name: %s\nbirthdate: %s",
                this.id,
                this.firstName,
                this.lastName,
                this.birthDate
        );
    }
}
