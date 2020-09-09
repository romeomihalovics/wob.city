package wob.city.controller;

import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.console.logger.ConsoleLogger;
import wob.city.city.City;
import wob.city.controller.services.ControlCenter;
import wob.city.controller.services.FoodLoader;
import wob.city.controller.services.PeopleGenerator;

import java.util.Collections;
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
        List<Person> people = Collections.synchronizedList(peopleGenerator.generatePeople(population));
        City city = new City(name, people, foods);
        city.getPeople().forEach(person -> person.setLocation(city));
        city.getPeople().forEach(Person::setWorkers);
        city.setWorkers();
        cities.add(city);
        ConsoleLogger.getLogger().log(city.toString());
    }

    public void validateCitizens(List<City> cities){
        controlCenter.validate(cities);
    }

    public void loadFoods(List<Food> foods) {
        foodLoader.loadFoods("foods.csv", foods);
    }
}
