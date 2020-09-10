package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class Earthquake extends Disaster {
    public Earthquake(String cause) {
        super("earthquake", "Earthquake", 30, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Earthquake -> The earth starts to shake");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Earthquake -> Electricity cuts off, a caos begins");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Earthquake -> People started to die");
        this.killPeople();
        this.getLocation().continueDisaster(new Aftershock("Earthquake"));
    }
}
