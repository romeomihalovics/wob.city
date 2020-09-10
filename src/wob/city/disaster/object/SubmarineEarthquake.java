package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class SubmarineEarthquake extends Disaster {
    public SubmarineEarthquake(String cause) {
        super("searthquake", "Submarine Earthquake", 0, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Submarine Earthquake -> Plate tectonic activity starts to begin under the sea the earth starts to shake");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Submarine Earthquake -> The plates move 3 meter apart");
    }

    @Override
    public void thirdWave() {
        this.getLocation().continueDisaster(new AftermathFlood("Submarine Earthquake"));
    }
}
