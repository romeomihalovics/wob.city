package wob.city.services;

import wob.city.abstractions.Person;
import wob.city.objects.*;
import wob.city.util.Calculations;

import java.util.List;

public class ControlCenter {
    public static void validate(List<City> cities) {
        for(City city : cities) {
            System.out.println("\n Validating random 10 people from city: " + city.getName());
            List<Person> randomTenPeople = Calculations.getRandomNPeople(city.getPeople(), 10);

            for (int i = randomTenPeople.size() - 1; i >= 0; i--) {
                Person person = city.getPeople().get(i);

                System.out.println("\n Validating person (" + person.getClass().getSimpleName() + "): " +
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

    public static void validateFirstName(Person person) {
        System.out.println("Has valid first name: " +
                (person instanceof Woman ? Person.names.isFemaleName(person.getFirstName()) : Person.names.isMaleName(person.getFirstName())));
    }

    public static void validateLastName(Person person) {
        System.out.println("Has valid last name: " +
                Person.names.isLastName(person.getLastName()));
    }

    public static void validateAge(Person person) {
        System.out.println("Has valid age: " +
                (person.getAge() >= person.getNormalMinAge() && person.getAge() <= person.getNormalMaxAge()));
    }

    public static void validateHeight(Person person) {
        System.out.println("Has valid height: " +
                (person.getHeight() >= person.getNormalMinHeight() && person.getHeight() <= person.getNormalMaxHeight()));
    }

    public static void validateWeight(Person person) {
        System.out.println("Has valid weight: " +
                (person.getWeight() >= person.getNormalMinWeight() && person.getWeight() <= person.getNormalMaxWeight()));
    }


}
