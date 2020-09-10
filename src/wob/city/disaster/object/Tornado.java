package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class Tornado extends Disaster {
    public Tornado(String cause) {
        super("tornado", "Tornado", 20, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Tornado -> A supercell started to form at the edge of the city");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Tornado -> It started to destroy objects in the city");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Tornado -> People started to die");
        this.killPeople();
        this.getLocation().finishDisaster();
    }
}
