package wob.city.controller.services;

import wob.city.city.City;
import wob.city.console.logger.ConsoleLogger;
import wob.city.person.abstraction.Person;
import wob.city.person.object.Woman;
import wob.city.util.Calculations;

import java.util.List;

public class ControlCenter {
    public void validate(List<City> cities) {
        for(City city : cities) {
            ConsoleLogger.getLogger().log("\n Validating random 10 people from city: " + city.getName());
            List<Person> randomTenPeople = Calculations.getRandomNPeople(city.getPeople(), 10);

            for (int i = randomTenPeople.size() - 1; i >= 0; i--) {
                Person person = city.getPeople().get(i);

                ConsoleLogger.getLogger().log("\n Validating person (" + person.getType().getValue() + "): " +
                        person.getFirstName() + " " +
                        person.getLastName());

                validateFirstName(person);
                validateLastName(person);
                validateAge(person);
                validateHeight(person);
                validateWeight(person);
            }
        }
    }

    public void validateFirstName(Person person) {
        ConsoleLogger.getLogger().log("Has valid first name: " +
                (person instanceof Woman ? Person.NAMES.isFemaleName(person.getFirstName()) : Person.NAMES.isMaleName(person.getFirstName())));
    }

    public void validateLastName(Person person) {
        ConsoleLogger.getLogger().log("Has valid last name: " +
                Person.NAMES.isLastName(person.getLastName()));
    }

    public void validateAge(Person person) {
        ConsoleLogger.getLogger().log("Has valid age: " +
                (person.getAge() >= person.getNormalMinAge() && person.getAge() <= person.getNormalMaxAge()));
    }

    public void validateHeight(Person person) {
        ConsoleLogger.getLogger().log("Has valid height: " +
                (person.getHeight() >= person.getNormalMinHeight() && person.getHeight() <= person.getNormalMaxHeight()));
    }

    public void validateWeight(Person person) {
        ConsoleLogger.getLogger().log("Has valid weight: " +
                (person.getWeight() >= person.getNormalMinWeight() && person.getWeight() <= person.getNormalMaxWeight()));
    }


}
