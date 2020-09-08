package wob.city.worker;

import wob.city.abstractions.Person;
import wob.city.logger.ActivityLogger;
import wob.city.objects.City;
import wob.city.services.PeopleGenerator;

import java.util.TimerTask;

public class NewBornWorker extends TimerTask {
    private final City city;
    private final PeopleGenerator peopleGenerator;

    public NewBornWorker(City city) {
        this.city = city;
        this.peopleGenerator = new PeopleGenerator();
    }

    @Override
    public void run() {
        Person person = peopleGenerator.generateNewBorn();
        person.setLocation(city);
        person.setWorkers();
        city.getPeople().add(person);

        ActivityLogger.getLogger().log("\nPerson: " + person.getFullName() +
                " just born into city: " + city.getName());
    }
}
