package pl.wsb.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LuggageService extends SpecialService {
    private double cost;

    public LuggageService(String name) {
        super(name);

        this.cost = 35.00;
    }

    public String orderService() {
        String information = "Hotel is currently storing customer luggage";
        return information;
    }

    public double calculateCost(int quantity) {
        return this.cost + quantity;
    }

    public boolean isAvailableOnDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Not avaliable in monday and saturday
        if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
