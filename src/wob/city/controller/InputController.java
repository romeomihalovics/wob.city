package wob.city.controller;

import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.console.logger.ConsoleLogger;
import wob.city.city.City;
import wob.city.util.CommandUtils;

import java.util.List;
import java.util.stream.Collectors;


public class InputController {
    public void listCities(List<City> cities) {
        ConsoleLogger.getLogger().log(cities.toString());
    }

    public void listFoods(List<Food> foods) {
        ConsoleLogger.getLogger().log("\n" + foods.stream().map(food -> food.toString(100)).collect(Collectors.joining()));
    }

    public void listPeople(List<City> cities, String input) {
        City city = CommandUtils.parseCityName(cities, input, false);
        ConsoleLogger.getLogger().log((city != null) ? city.getPeople().toString() : "City not found");
    }

    public void getPerson(List<City> cities, String input) {
        City city = CommandUtils.parseCityName(cities, input, true);
        if(city != null) {
            Person person = CommandUtils.getPerson(city, input);
            ConsoleLogger.getLogger().log((person != null) ? person.toString() : "Person not found");
        } else {
            ConsoleLogger.getLogger().log("City not found");
        }
    }

    public void getFood(List<Food> foods, String input) {
        Food food = CommandUtils.parseFoodName(foods, input, false);
        ConsoleLogger.getLogger().log((food != null) ? food.toString(100) : "Food not found");
    }

    public void getFoodByGramm(List<Food> foods, String input) {
        Food food = CommandUtils.parseFoodName(foods, input, true);
        Integer gramm = CommandUtils.parseFoodGramm(input);
        ConsoleLogger.getLogger().log((food != null) ? food.toString(gramm) : "Food not found");
    }

    public void getHelp() {
        ConsoleLogger.getLogger().log("\n -- COMMANDS --" +
                "\n  cities -- List cities" +
                "\n  people -c City Name -- List all people in a city" +
                "\n  person -c City Name -n Person Name -- Get details about a specific person in a city" +
                "\n  foods -- List all available food" +
                "\n  food -n name -- Get details about a specific food" +
                "\n  food -n name -gramm 100 -- Get details about a specific food, with a specific weight" +
                "\n  help -- List available commands" +
                "\n  exit -- Exit application");
    }

    public void exitApp(){
        ConsoleLogger.getLogger().log("Closing Application");
        System.exit(1);
    }
}
