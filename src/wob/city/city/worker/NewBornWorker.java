package wob.city.city.worker;

import wob.city.person.abstraction.Person;
import wob.city.console.logger.ActivityLogger;
import wob.city.city.City;
import wob.city.controller.services.PeopleGenerator;

import java.util.Collections;
import java.util.List;
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

        List<Person> people = Collections.synchronizedList(city.getPeople());
        synchronized (people) {
            people.add(person);
        }

        city.getNewBornNews().addData(person);

        ActivityLogger.getLogger().log("\nPerson: " + person.getFullName() +
                " just born into city: " + city.getName());
    }
}
