package pl.wsb;

import java.time.LocalDate;

public class PremiumClient extends Client {
    private PremiumAccountType premiumAccountType;

    public PremiumClient(String id, String firstName, String lastName, LocalDate birthDate, String address, String phoneNumber,
                         String email, String idCardNumber, Preference roomPreference, PremiumAccountType premiumAccountType
    ) {
        super(id, firstName, lastName, birthDate, address, phoneNumber, email, idCardNumber, roomPreference);
        this.premiumAccountType = premiumAccountType;
    }

    @Override
    public String getFullName() {
        return "[premium] " + this.firstName + " " + this.lastName;
    }

    public void setPremiumAccountType(PremiumAccountType premiumAccountType) {
        this.premiumAccountType = premiumAccountType;
    }

    public PremiumAccountType getPremiumAccountType() {
        return this.premiumAccountType;
    }
}
