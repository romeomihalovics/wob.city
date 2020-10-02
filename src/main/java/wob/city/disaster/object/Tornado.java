package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.DtoGenerator;

public class Tornado extends Disaster {
    public Tornado(String cause) {
        super("tornado", "Tornado", 20, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Tornado -> A super cell started to form at the edge of the city";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Tornado -> It started to destroy objects in the city";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Tornado -> People started to die";
        ConsoleLogger.getLogger().log(event);
        killPeople();
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
        return Timing.DEFAULT_DISASTER_THIRD_WAVE;
    }

    @Override
    public DeathCause getDeathCause() {
        return DeathCause.DISASTER_TORNADO;
    }
}
