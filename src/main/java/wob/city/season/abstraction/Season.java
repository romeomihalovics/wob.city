package wob.city.season.abstraction;

import java.time.LocalDateTime;

public abstract class Season {
    protected int daysElapsed = 0;

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void addElapsedDay() {
        daysElapsed++;
    }

    public abstract double getMinTemperature(LocalDateTime time);
    public abstract double getMaxTemperature(LocalDateTime time);
    public abstract String getName();
}
