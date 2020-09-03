package wob.city.controller;

import wob.city.Main;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.services.PeopleGenerator;

import java.util.List;

public class TaskController {
    private final PeopleGenerator peopleGenerator = new PeopleGenerator();

    public void createCity(String name, Integer population) {
        List<Person> people = peopleGenerator.generatePeople(population);
        City city = new City(name, people, population);
        Main.cities.add(city);
    }
}
