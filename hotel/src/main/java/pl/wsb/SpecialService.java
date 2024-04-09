package pl.wsb;

import java.time.LocalDate;

public abstract class SpecialService {
    protected String name;

    public SpecialService(String name) {
        this.name = name;
    }

    public abstract void orderService();

    public abstract double calculateCost(int quantity);

    public abstract boolean isAvailableOnDate(LocalDate date);
}
