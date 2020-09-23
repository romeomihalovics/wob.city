package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.util.DtoGenerator;

public class SubmarineEarthquake extends Disaster {
    public SubmarineEarthquake(String cause) {
        super("searthquake", "Submarine Earthquake", 0, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Submarine Earthquake -> Plate tectonic activity starts to begin under the sea the earth starts to shake";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Submarine Earthquake -> The plates move 3 meter apart";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        this.getLocation().continueDisaster(new AftermathFlood("Submarine Earthquake"));
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
