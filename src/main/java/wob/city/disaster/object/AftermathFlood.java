package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;
import wob.city.util.DtoGenerator;

public class AftermathFlood extends Disaster implements Consequence {

    public AftermathFlood(String cause) {
        super("aftermathflood", "Aftermath Flood", 40, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Aftermath Flood -> Enormous waves starts to form because of the " + cause;
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Aftermath Flood -> The waves reached the city and flood it";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Aftermath Flood -> People started to die";
        ConsoleLogger.getLogger().log(event);
        this.killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
        this.getLocation().finishDisaster();
    }
}
