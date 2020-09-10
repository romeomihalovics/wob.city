package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class Flood extends Disaster {

    public Flood(String cause) {
        super("flood", "Flood", 40, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Flood -> Next to the city a meteor falls into the sea, causing enormous waves");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Flood -> The waves reach the city and flood it");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Flood -> People started to die");
        this.killPeople();
        this.getLocation().finishDisaster();
    }
}
