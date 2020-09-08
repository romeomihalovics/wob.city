package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class Woman extends Person {
    public Woman(){
        super(50, 65, names.getFemaleName(), Calculations.getRandomIntBetween(50, 65));
    }

    public Woman(Girl newAdult) {
        super(newAdult);
    }
}
