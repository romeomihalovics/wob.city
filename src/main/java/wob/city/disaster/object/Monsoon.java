package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.food.object.Vegetable;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.DtoGenerator;

public class Monsoon extends Disaster {
    public Monsoon(String cause) {
        super("monsoon", "Monsoon", 0, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Monsoon -> Its the first week of the month, and the monsoon just started.";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        getLocation().getCutOnFood().put(Vegetable.class, 0.5);

        String event = "Second Wave of Monsoon -> Because of the monsoon, production of vegetable will drop to 50%";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Monsoon -> The monsoon has come to an end, the production of foods back to normal.";
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
        return Timing.MONSOON_THIRD_WAVE;
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
