package pl.wsb;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class PremiumClient extends Client {
    protected premiumAccountType premiumAccountType;

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber,
                         String email, String idCardNumber, Preference roomPreference, premiumAccountType premiumAccountType
    ) {
        super(id, firstName, lastName, birthDate, address, phoneNumber, email, idCardNumber, roomPreference);
        this.premiumAccountType = premiumAccountType;
    }

    @Override
    public String getFullName() {
        return "[premium] " + this.firstName + " " + this.lastName;
    }

    public void setPremiumAccountType(premiumAccountType premiumAccountType) {
        this.premiumAccountType = premiumAccountType;
    }

    public premiumAccountType getPremiumAccountType() {
        return this.premiumAccountType;
    }
}
