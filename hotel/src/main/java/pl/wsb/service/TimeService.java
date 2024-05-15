package pl.wsb.service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class TimeService extends SpecialService {
    private double cost;

    public TimeService(String name) {
        super(name);

        this.cost = 30.00;
    }

    public String orderService() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(now);
    }

    public double calculateCost(int quantity) {
        return this.cost * quantity;
    }

    public boolean isAvailableOnDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Not avaliable in weekend
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "" + name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }
}
