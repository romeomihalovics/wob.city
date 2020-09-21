package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.util.DtoGenerator;

public class Tornado extends Disaster {
    public Tornado(String cause) {
        super("tornado", "Tornado", 20, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Tornado -> A super cell started to form at the edge of the city";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Tornado -> It started to destroy objects in the city";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Tornado -> People started to die";
        ConsoleLogger.getLogger().log(event);
        this.killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
        this.getLocation().finishDisaster();
    }
}
