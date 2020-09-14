package wob.city.family;

import wob.city.city.City;
import wob.city.housing.abstraction.Housing;
import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class Family {
    private final City location;
    private final List<Person> people;
    private final List<Person> deaths;
    private final String familyName;
    private Housing house;

    public Family(City location, Person founder) {
        this.people = new ArrayList<>();
        this.deaths = new ArrayList<>();
        this.location = location;
        this.familyName = founder.getLastName();
        this.people.add(founder);
    }

    public void tryToAdd(Person person) {
        if(person.getFamily() == null){
            if(person.getType().equals("Man") || person.getType().equals("Woman")) {
                if(Calculations.getPeopleCountByType(people, person.getType()) == 0 && Calculations.getRandomIntBetween(0,100) <= 50) {
                    people.add(person);
                    person.setFamily(this);
                    person.setStatInFamily("Parent");
                }
            }else {
                if ((Calculations.getPeopleCountByType(people, "Boy") + Calculations.getPeopleCountByType(people, "Girl")) < 3 && Calculations.getRandomIntBetween(0, 100) <= 20) {
                    people.add(person);
                    person.setFamily(this);
                    person.setLastName(familyName);
                    person.setStatInFamily("Child");
                }
            }
        }
    }

    public void findHousing(){

    }

    public void addDied(Person person) {
        people.remove(person);
        deaths.add(person);
    }

    public City getLocation() {
        return location;
    }

    public List<Person> getPeople() {
        return people;
    }
}
