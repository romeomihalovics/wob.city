package wob.city.factory;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class KidFactory {
    public static void setDetails(Person person) {
        person.setNormalMaxWeight(60);
        person.setNormalMinWeight(20);
        person.setWeight(Calculations.getRandomIntBetween(20, 60));
        person.setAge(Calculations.getRandomIntBetween(1, 18));
    }
}
