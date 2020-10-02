package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.disaster.enums.TemperatureLimit;
import wob.city.food.object.Grain;
import wob.city.food.object.Vegetable;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.DtoGenerator;

public class Drought extends Disaster {
    public Drought(String cause) {
        super("drought", "Drought", 0, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Drought -> The temperature for the past 3 days was over " + TemperatureLimit.DROUGHT + " degrees, so a drought started";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        getLocation().getCutOnFood().put(Grain.class, 0.5);
        getLocation().getCutOnFood().put(Vegetable.class, 0.5);

        String event = "Second Wave of Drought -> Because of the drought there is a 50% cut on grain & vegetable in the food factory";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Drought -> The drought has come to an end, the production of foods back to normal.";
        ConsoleLogger.getLogger().log(event);
        getLocation().removeCutOnFood();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
        getLocation().finishDisaster();
    }

    @Override
    public Timing getFirstWaveTiming() {
        return Timing.DEFAULT_DISASTER_FIRST_WAVE;
    }

    @Override
    public Timing getSecondWaveTiming() {
        return Timing.DEFAULT_DISASTER_SECOND_WAVE;
    }

    @Override
    public Timing getThirdWaveTiming() {
        return Timing.DROUGHT_THIRD_WAVE;
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
