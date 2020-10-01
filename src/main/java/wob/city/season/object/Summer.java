package wob.city.season.object;

import wob.city.season.abstraction.Season;
import wob.city.util.Calculation;

import java.time.LocalDateTime;

public class Summer extends Season {
    @Override
    public double getMinTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = 12;
                break;
            case AFTERNOON:
                maxTemperature = 14.5;
                break;
            case EVENING:
                maxTemperature = 12.4;
                break;
            default:
                maxTemperature = 13;
                break;
        }
        return maxTemperature;
    }

    @Override
    public double getMaxTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = 22.1;
                break;
            case AFTERNOON:
                maxTemperature = 42.3;
                break;
            case EVENING:
                maxTemperature = 27.3;
                break;
            default:
                maxTemperature = 42;
                break;
        }
        return maxTemperature;
    }

    @Override
    public String getName() {
        return "Summer";
    }
}
