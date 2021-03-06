package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.person.enums.Type;
import wob.city.util.Calculation;

import java.util.Collections;
import java.util.List;

public class Girl extends Woman {
    public Girl() {
        super(20, 60, Calculation.getRandomIntBetween(20, 60), Calculation.getRandomIntBetween(1, 18));
    }

    public Girl(boolean isNewBorn) {
        super(20, 60, Calculation.getRandomIntBetween(20, 60), 1);
    }

    @Override
    public Type getType() {
        return Type.GIRL;
    }

    @Override
    public void doAging() {
        addAge();
        if(getAge() >= 18) {
            Person newAdult = new Woman(this);
            family.getPeople().remove(this);
            family.getPeople().add(newAdult);
            List<Person> people = Collections.synchronizedList(getLocation().getPeople());

            synchronized (people) {
                people.remove(this);
                people.add(newAdult);
            }

            if(Calculation.getRandomIntBetween(0, 100) <= 10) {
                newAdult.die(DeathCause.AGING);
            }
        }
    }
}
