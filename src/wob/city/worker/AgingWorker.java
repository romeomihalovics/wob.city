package wob.city.worker;

import wob.city.abstractions.Person;
import wob.city.objects.Boy;
import wob.city.objects.Girl;
import wob.city.objects.Man;
import wob.city.objects.Woman;
import wob.city.util.Calculations;

import java.util.TimerTask;

public class AgingWorker extends TimerTask {
    private final Person person;

    public AgingWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.setAge(person.getAge() + 1);
        if(person.getAge() >= 18 && (person instanceof Girl || person instanceof Boy)) {
            Person newAdult;
            if(person instanceof Girl) {
               newAdult = new Woman((Girl) person);
            }else{
               newAdult = new Man((Boy) person);
            }
            person.getLocation().getPeople().remove(person);
            person.getLocation().getPeople().add(newAdult);

            if(Calculations.getRandomIntBetween(0, 100) <= 10) {
                newAdult.die();
            }
        }else if(person.getAge() >= 60 && Calculations.getRandomIntBetween(0, 100) <= 25) {
            person.die();
        }
    }
}
