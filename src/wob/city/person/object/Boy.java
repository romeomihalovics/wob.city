package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;

import java.util.Collections;
import java.util.List;

public class Boy extends Man {
    public Boy() {
        super(20, 60, Calculations.getRandomIntBetween(20, 60), Calculations.getRandomIntBetween(1, 18));
    }

    public Boy(boolean isNewBorn) {
        super(20, 60, Calculations.getRandomIntBetween(20, 60), 1);
    }

    @Override
    public String getType() {
        return "Boy";
    }

    @Override
    public void doAging() {
        this.addAge();
        if (this.getAge() >= 18) {
            Person newAdult = new Man(this);
            family.getPeople().remove(this);
            family.getPeople().add(newAdult);
            List<Person> people = Collections.synchronizedList(this.getLocation().getPeople());

            synchronized (people) {
                people.remove(this);
                people.add(newAdult);
            }

            if (Calculations.getRandomIntBetween(0, 100) <= 10) {
                newAdult.die();
            }
        }
    }
}
