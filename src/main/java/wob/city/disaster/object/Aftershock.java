package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.util.DtoGenerator;

public class Aftershock extends Disaster implements Consequence {
    public Aftershock(String cause) {
        super("aftershock", "Aftershock", 10, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Aftershock -> The ground starts to shake again because of the previous " + cause;
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "First Wave of Aftershock -> The caos continues";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Aftershock -> People started to die";
        ConsoleLogger.getLogger().log(event);
        this.killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
        this.getLocation().finishDisaster();
    }

    @Override
    public DeathCause getDeathCause() {
        return DeathCause.DISASTER_AFTERSHOCK;
    }
}
