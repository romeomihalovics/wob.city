package wob.city.services;

import wob.city.abstractions.Person;
import wob.city.objects.Boy;
import wob.city.objects.Girl;
import wob.city.objects.Man;
import wob.city.objects.Woman;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class PeopleGenerator {

    public static List<Person> generatePeople(Integer population){
        List<Person> people = new ArrayList<>();

        while(people.size() < population){
            people.add(generatePerson());
        }

        return people;
    }

    private static Person generatePerson() {
        Person person;
        switch(Calculations.getRandomIntBetween(1, 4)) {
            case 1:
                person = new Woman();
                break;
            case 2:
                person = new Girl();
                break;
            case 3:
                person = new Man();
                break;
            default:
                person = new Boy();
                break;
        }
        return person;
    }
}
