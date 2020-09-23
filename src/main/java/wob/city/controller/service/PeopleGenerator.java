package wob.city.controller.service;

import wob.city.person.abstraction.Person;
import wob.city.person.object.Boy;
import wob.city.person.object.Girl;
import wob.city.person.object.Man;
import wob.city.person.object.Woman;
import wob.city.util.Calculation;

import java.util.ArrayList;
import java.util.List;

public class PeopleGenerator {

    public List<Person> generatePeople(Integer population){
        List<Person> people = new ArrayList<>();

        while(people.size() < population){
            people.add(generatePerson());
        }

        return people;
    }

    private Person generatePerson() {
        Person person;
        switch(Calculation.getRandomIntBetween(1, 4)) {
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

    public Person generateNewBorn() {
        Person person;
        if (Calculation.getRandomIntBetween(1, 2) == 1) {
            person = new Girl(true);
        } else {
            person = new Boy(true);
        }
        return person;
    }
}
