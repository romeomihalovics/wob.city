package wob.city.season.object;

import wob.city.season.abstraction.Season;
import wob.city.util.Calculation;

import java.time.LocalDateTime;

public class Autumn extends Season {
    @Override
    public double getMinTemperature(LocalDateTime time) {
        double minTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                minTemperature = 1.4;
                break;
            case AFTERNOON:
                minTemperature = 10.7;
                break;
            case EVENING:
                minTemperature = 3;
                break;
            default:
                minTemperature = 10;
                break;
        }
        return minTemperature;
    }

    @Override
    public double getMaxTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = 7.4;
                break;
            case AFTERNOON:
                maxTemperature = 27.4;
                break;
            case EVENING:
                maxTemperature = 6.7;
                break;
            default:
                maxTemperature = 27;
                break;
        }
        return maxTemperature;
    }
}
