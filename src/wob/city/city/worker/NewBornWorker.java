package wob.city.city.worker;

import wob.city.city.City;
import wob.city.console.logger.ActivityLogger;
import wob.city.controller.services.PeopleGenerator;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.family.Family;
import wob.city.person.abstraction.Person;
import wob.city.util.DtoGenerator;

import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class NewBornWorker extends TimerTask {
    private final City city;
    private final PeopleGenerator peopleGenerator;
    private final NewsPaperDao newsPaperDao = new NewsPaperDao();
    private final PersonHistoryDao personHistoryDao = new PersonHistoryDao();

    public NewBornWorker(City city) {
        this.city = city;
        this.peopleGenerator = new PeopleGenerator();
    }

    @Override
    public void run() {
        Family family;
        if((family = this.city.getFertileFamily()) != null) {
            Person person = peopleGenerator.generateNewBorn();
            person.setLocation(city);
            person.setWorkers();
            family.tryToAdd(person, true);

            List<Person> people = Collections.synchronizedList(city.getPeople());
            synchronized (people) {
                people.add(person);
            }

            String event = "\nPerson: " + person.getFullName() +
                    " just born into city: " + city.getName();
            ActivityLogger.getLogger().log(event);
            personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, person));
            newsPaperDao.uploadPersonNews("new_born", DtoGenerator.setupPersonNewsDto(person));
        }
    }
}
