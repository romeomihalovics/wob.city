package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.util.DtoGenerator;

public class Earthquake extends Disaster {
    public Earthquake(String cause) {
        super("earthquake", "Earthquake", 30, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Earthquake -> The earth starts to shake";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Earthquake -> Electricity cuts off, a caos begins";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Earthquake -> People started to die";
        ConsoleLogger.getLogger().log(event);
        killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
        getLocation().continueDisaster(new Aftershock("Earthquake"));
    }

    @Override
    public DeathCause getDeathCause() {
        return DeathCause.DISASTER_EARTHQUAKE;
    }
}
