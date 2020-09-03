package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class Woman extends Person {
    public Woman(){
        this.setFirstName(Person.names.getFemaleName());
        this.setWeight(Calculations.getRandomIntBetween(50, 65));
    }
}
