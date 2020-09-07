package wob.city.controller;

import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.services.ControlCenter;
import wob.city.services.FoodLoader;
import wob.city.services.PeopleGenerator;

import java.util.List;

public class TaskController {
    private final ControlCenter controlCenter;
    private final FoodLoader foodLoader;
    private final PeopleGenerator peopleGenerator;

    public TaskController() {
        this.controlCenter = new ControlCenter();
        this.foodLoader = new FoodLoader();
        this.peopleGenerator = new PeopleGenerator();
    }

    public void createCity(String name, Integer population, List<City> cities, List<Food> foods) {
        List<Person> people = peopleGenerator.generatePeople(population);
        City city = new City(name, people, population, foods);
        city.getPeople().forEach(person -> person.setLocation(city));
        city.getPeople().forEach(Person::setWorkers);
        cities.add(city);
        System.out.print(city);
    }

    public void validateCitizens(List<City> cities){
        controlCenter.validate(cities);
    }

    public void loadFoods(List<Food> foods) {
        foodLoader.loadFoods("foods.csv", foods);
    }
}
