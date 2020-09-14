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
    private Housing house = null;

    public Family(City location, Person founder) {
        this.people = new ArrayList<>();
        this.deaths = new ArrayList<>();
        this.location = location;
        this.familyName = founder.getLastName();
        this.people.add(founder);
    }

    public void tryToAdd(Person person, boolean mustAdd) {
        if(person.getFamily() == null){
            if(person.getType().equals("Man") || person.getType().equals("Woman")) {
                if(Calculations.getPeopleCountByType(people, person.getType()) == 0 && (Calculations.getRandomIntBetween(0,100) <= 50 || mustAdd)) {
                    people.add(person);
                    person.setFamily(this);
                    person.setStatInFamily("Parent");
                }
            }else {
                if ((Calculations.getPeopleCountByType(people, "Boy") + Calculations.getPeopleCountByType(people, "Girl")) < 3 && (Calculations.getRandomIntBetween(0, 100) <= 20 || mustAdd)) {
                    people.add(person);
                    person.setFamily(this);
                    person.setLastName(familyName);
                    person.setStatInFamily("Child");
                }
            }
        }
    }

    public void findHousing(){
        for(Housing house : location.getHouses()){
            if(house.getFamilies().size() < house.maxFamilies()) {
                house.addFamily(this);
                setHouse(house);
                break;
            }
        }
        if(house == null) {
            setHouse(location.buildHouse());
        }
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

    private void setHouse(Housing housing) {
        this.house = housing;
    }
}
