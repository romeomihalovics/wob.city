package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;

public class Volcano extends Disaster {
    public Volcano(String cause) {
        super("volcano", "Volcanic Eruption", 50, cause);
    }

    @Override
    public void firstWave() {
        ConsoleLogger.getLogger().log("First Wave of Volcanic Eruption -> The ground starts to shake, people start to panic");
    }

    @Override
    public void secondWave() {
        ConsoleLogger.getLogger().log("Second Wave of Volcanic Eruption -> The lava destroys objects in the city");
    }

    @Override
    public void thirdWave() {
        ConsoleLogger.getLogger().log("Third Wave of Volcanic Eruption -> People started to die");
        this.killPeople();
        this.getLocation().finishDisaster();
    }
}
