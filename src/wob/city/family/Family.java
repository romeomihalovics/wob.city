package wob.city.family;

import wob.city.city.City;
import wob.city.housing.abstraction.Housing;
import wob.city.person.abstraction.Person;

import java.util.ArrayList;
import java.util.List;

public class Family {
    private final City location;
    private final List<Person> people;
    private final List<Person> deaths;
    private Housing house;

    public Family(City location) {
        this.people = new ArrayList<>();
        this.deaths = new ArrayList<>();
        this.location = location;

    }

    public City getLocation() {
        return location;
    }
}
