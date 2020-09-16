package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.util.DtoGenerator;

public class Volcano extends Disaster {
    public Volcano(String cause) {
        super("volcano", "Volcanic Eruption", 50, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Volcanic Eruption -> The ground starts to shake, people start to panic";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void secondWave() {
        String event = "Second Wave of Volcanic Eruption -> The lava destroys objects in the city";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Volcanic Eruption -> People started to die";
        ConsoleLogger.getLogger().log(event);
        this.killPeople();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this.location));
        this.getLocation().finishDisaster();
    }
}
