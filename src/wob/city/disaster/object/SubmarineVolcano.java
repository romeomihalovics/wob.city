package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class SubmarineVolcano extends Disaster {
    public SubmarineVolcano(String cause) {
        super("svolcano", "Submarine Volcanic Eruption", 0, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Submarine Volcanic Eruption -> At the bottom of the sea a volcano becomes active");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Submarine Volcanic Eruption -> The volcano erupts, lava gets into the sea");
    }

    @Override
    public void thirdWave() {
        this.getLocation().continueDisaster(new AftermathFlood("Submarine Volcanic Eruption"));
    }
}
