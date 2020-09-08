package wob.city.worker;

import wob.city.abstractions.Person;
import wob.city.logger.ActivityLogger;

import java.util.TimerTask;

public class DigestionWorker extends TimerTask {
    private final Person person;

    public DigestionWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run(){
        person.setEnergy(person.getEnergy() - 350);

        ActivityLogger.getLogger().log("\nPerson: " + person.getFullName() +
                " burned 350kcal, his/her energy levels changed from " +
                (person.getEnergy() + 350) + "kcal to " + person.getEnergy() + "kcal");

        if(person.getEnergy() <= 0) {
            person.die();
        }
    }
}
