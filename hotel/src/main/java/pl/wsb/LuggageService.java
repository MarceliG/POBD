package pl.wsb;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LuggageService extends SpecialService {
    private double cost;

    public LuggageService(String name) {
        super(name);

        this.cost = 35.00;
    }

    public void orderService()  {
        System.out.println("Hotel aktualnie przechowuje bagaż klienta");
    }

    public double calculateCost(int quantity) {
        return this.cost + quantity;
    }

    public boolean isAvailableOnDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Not avaliable in monday and saturday
        if(dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return false;
        } else {
            return true;
        }
    }
}
