package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class Man extends Person {
    public Man(){
        this.setFirstName(Person.names.getMaleName());
        this.setWeight(Calculations.getRandomIntBetween(65, 120));
    }
}
