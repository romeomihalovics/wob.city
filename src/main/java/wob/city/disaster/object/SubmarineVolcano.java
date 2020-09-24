package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.util.DtoGenerator;

public class SubmarineVolcano extends Disaster {
    public SubmarineVolcano(String cause) {
        super("svolcano", "Submarine Volcanic Eruption", 0, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Submarine Volcanic Eruption -> At the bottom of the sea a volcano becomes active";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Submarine Volcanic Eruption -> The volcano erupts, lava gets into the sea";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        getLocation().continueDisaster(new AftermathFlood("Submarine Volcanic Eruption"));
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
