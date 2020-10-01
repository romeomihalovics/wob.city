package wob.city.season.object;

import wob.city.season.abstraction.Season;
import wob.city.util.Calculation;

import java.time.LocalDateTime;

public class Winter extends Season {
    @Override
    public double getMinTemperature(LocalDateTime time) {
        double minTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                minTemperature = -15.98;
                break;
            case AFTERNOON:
                minTemperature = -5.33;
                break;
            case EVENING:
                minTemperature = -12;
                break;
            default:
                minTemperature = -5;
                break;
        }
        return minTemperature;
    }

    @Override
    public double getMaxTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = -1.98;
                break;
            case AFTERNOON:
                maxTemperature = 2.12;
                break;
            case EVENING:
                maxTemperature = -2;
                break;
            default:
                maxTemperature = 2;
                break;
        }
        return maxTemperature;
    }

    @Override
    public String getName() {
        return "Winter";
    }
}
