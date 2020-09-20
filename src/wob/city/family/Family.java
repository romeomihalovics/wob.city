package wob.city.family;

import wob.city.city.City;
import wob.city.housing.abstraction.Housing;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Types;
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
            if(person.getType() == Types.MAN || person.getType() == Types.WOMAN) {
                tryToFitAsParent(person, mustAdd);
            } else {
                tryToFitAsChild(person, mustAdd);
            }
        }
    }

    private void tryToFitAsParent(Person person, boolean mustAdd) {
        if(Calculations.getPeopleCountByType(people, person.getType()) == 0 && (Calculations.getRandomIntBetween(0,100) <= 50 || mustAdd)) {
            people.add(person);
            person.setFamily(this);
            person.setStatInFamily(StatInFamily.PARENT);
        }
    }

    private void tryToFitAsChild(Person person, boolean mustAdd) {
        if ((Calculations.getPeopleCountByType(people, Types.BOY) + Calculations.getPeopleCountByType(people, Types.GIRL)) < 3 && (Calculations.getRandomIntBetween(0, 100) <= 20 || mustAdd)) {
            people.add(person);
            person.setFamily(this);
            person.setLastName(familyName);
            person.setStatInFamily(StatInFamily.CHILD);
        }
    }

    public void findHousing(){
        for(Housing housing : location.getHouses()){
            if(housing.getFamilies().size() < housing.maxFamilies()) {
                housing.addFamily(this);
                setHouse(housing);
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
