package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;

public class Aftershock extends Disaster implements Consequence {
    public Aftershock(String cause) {
        super("aftershock", "Aftershock", 10, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Aftershock -> The ground starts to shake again because of the previous " + cause);
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("First Wave of Aftershock -> The caos continues");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Aftershock -> People started to die");
        this.killPeople();
        this.getLocation().finishDisaster();
    }
}
