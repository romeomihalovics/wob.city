package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.DtoGenerator;

public class Flood extends Disaster {

    public Flood(String cause) {
        super("flood", "Flood", 40, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Flood -> Next to the city a meteor falls into the sea, causing enormous waves";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Flood -> The waves reach the city and flood it";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Flood -> People started to die";
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
        return DeathCause.DISASTER_FLOOD;
    }
}
