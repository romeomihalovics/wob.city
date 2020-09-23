package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.util.DtoGenerator;

public class Earthquake extends Disaster {
    public Earthquake(String cause) {
        super("earthquake", "Earthquake", 30, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Earthquake -> The earth starts to shake";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Earthquake -> Electricity cuts off, a caos begins";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Earthquake -> People started to die";
        ConsoleLogger.getLogger().log(event);
        this.killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
        this.getLocation().continueDisaster(new Aftershock("Earthquake"));
    }
}