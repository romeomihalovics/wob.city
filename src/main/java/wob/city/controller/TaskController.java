package wob.city.controller;

import wob.city.city.City;
import wob.city.console.logger.ConsoleLogger;
import wob.city.controller.service.ControlCenter;
import wob.city.controller.service.FoodRecipeLoader;
import wob.city.controller.service.PeopleGenerator;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;

import java.util.Collections;
import java.util.List;

public class TaskController {
    private final ControlCenter controlCenter;
    private final FoodRecipeLoader foodRecipeLoader;
    private final PeopleGenerator peopleGenerator;

    public TaskController() {
        this.controlCenter = new ControlCenter();
        this.foodRecipeLoader = new FoodRecipeLoader();
        this.peopleGenerator = new PeopleGenerator();
    }

    public void createCity(String name, Integer population, List<City> cities, List<Food> foods) {
        List<Person> people = Collections.synchronizedList(peopleGenerator.generatePeople(population));

        City city = new City(name, people, foods);

        city.getPeople().forEach(person -> {
            person.setLocation(city);
            person.setProfession();
            person.setWorkers();
        });

        city.createFamilies();

        city.setWorkers();
        cities.add(city);

        ConsoleLogger.getLogger().log(city.toString());
    }

    public void validateCitizens(List<City> cities){
        controlCenter.validate(cities);
    }

    public void loadFoods(List<Food> foods) {
        foodRecipeLoader.loadFoods("foods.csv", foods);
    }
}
