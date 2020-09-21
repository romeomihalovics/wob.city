package wob.city.controller;

import wob.city.city.City;
import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
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
        City city = CommandUtils.parseCityName(cities, input, "people", false);
        ConsoleLogger.getLogger().log((city != null) ? city.getPeople().toString() : "City not found");
    }

    public void getPerson(List<City> cities, String input) {
        City city = CommandUtils.parseCityName(cities, input, "person", true);
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
                "\n  disasters -- List disaster types" +
                "\n  disaster -c City Name -t Disaster Type -- Start a natural disaster in a city" +
                "\n  people -c City Name -- List all people in a city" +
                "\n  person -c City Name -n Person Name -- Get details about a specific person in a city" +
                "\n  foods -- List all available food" +
                "\n  food -n name -- Get details about a specific food" +
                "\n  food -n name -gramm 100 -- Get details about a specific food, with a specific weight" +
                "\n  help -- List available commands" +
                "\n  exit -- Exit application");
    }

    public void listDisasters() {
        ConsoleLogger.getLogger().log("\n -- DISASTER TYPES --" +
                "\n  Volcanic eruption -- disaster -c City Name -n volcano" +
                "\n  Tornado -- disaster -c City Name -n tornado" +
                "\n  Flood -- disaster -c City Name -n flood" +
                "\n  Submarine volcanic eruption -- disaster -c City Name -n svolcano" +
                "\n  Earthquake -- disaster -c City Name -n earthquake" +
                "\n  Submarine earthquake -- disaster -c City Name -n searthquake");
    }

    public void startDisaster(List<City> cities, String input) {
        City city = CommandUtils.parseCityName(cities, input, "disaster", true);
        if (city != null) {
            Disaster disaster = CommandUtils.getDisaster(input);
            if(disaster != null) {
                disaster.setLocation(city);
                city.startDisaster(disaster);
            } else {
                ConsoleLogger.getLogger().log("Invalid disaster name");
            }
        } else {
            ConsoleLogger.getLogger().log("City not found");
        }
    }

    public void exitApp(){
        ConsoleLogger.getLogger().log("Closing Application");
        System.exit(1);
    }
}
