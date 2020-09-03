package wob.city.controller;

import wob.city.Main;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.services.ControlCenter;
import wob.city.services.PeopleGenerator;

import java.util.List;

public class TaskController {
    public static void createCity(String name, Integer population) {
        List<Person> people = PeopleGenerator.generatePeople(population);
        City city = new City(name, people, population);
        Main.cities.add(city);
        System.out.print(city);
    }

    public static void validateCitizens(List<City> cities){
        ControlCenter.validate(cities);
    }
}
