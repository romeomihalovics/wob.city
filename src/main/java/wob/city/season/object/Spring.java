package wob.city.season.object;

import wob.city.season.abstraction.Season;
import wob.city.util.Calculation;

import java.time.LocalDateTime;

public class Spring extends Season {
    @Override
    public double getMinTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = -0.88;
                break;
            case AFTERNOON:
                maxTemperature = 4.88;
                break;
            case EVENING:
                maxTemperature = -1;
                break;
            default:
                maxTemperature = 4;
                break;
        }
        return maxTemperature;
    }

    @Override
    public double getMaxTemperature(LocalDateTime time) {
        double maxTemperature;
        switch (Calculation.getPartOfDay(time)) {
            case MORNING:
                maxTemperature = 8.2;
                break;
            case AFTERNOON:
                maxTemperature = 27.9;
                break;
            case EVENING:
                maxTemperature = 7;
                break;
            default:
                maxTemperature = 27;
                break;
        }
        return maxTemperature;
    }

    @Override
    public String getName() {
        return "Spring";
    }
}
