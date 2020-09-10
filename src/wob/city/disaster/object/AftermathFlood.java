package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;

public class AftermathFlood extends Disaster implements Consequence {

    public AftermathFlood(String cause) {
        super("aftermathflood", "Aftermath Flood", 40, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Aftermath Flood -> Enormous waves starts to form because of the " + cause);
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Aftermath Flood -> The waves reached the city and flood it");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Aftermath Flood -> People started to die");
        this.killPeople();
        this.getLocation().finishDisaster();
    }
}
