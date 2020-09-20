package wob.city.person.object;

import wob.city.person.abstraction.Person;
import wob.city.person.enums.Types;
import wob.city.util.Calculations;

public class Woman extends Person {
    public Woman(){
        super(50, 65, NAMES.getFemaleName(), Calculations.getRandomIntBetween(50, 65));
    }

    public Woman(int normalMinWeight, int normalMaxWeight, int weight, int age) {
        super(normalMinWeight, normalMaxWeight, weight, age, NAMES.getFemaleName());
    }

    public Woman(Girl newAdult) {
        super(newAdult);
    }

    @Override
    public Types getType() {
        return Types.WOMAN;
    }

    @Override
    public void doAging() {
        this.addAge();
        if((this.getAge() >= 60 && Calculations.getRandomIntBetween(0, 100) <= 25) || this.getAge() >= Person.ABSOLUTE_MAX_AGE) {
            this.die();
        }
    }
}
