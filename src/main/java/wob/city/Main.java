package wob.city;

import wob.city.city.City;
import wob.city.console.input.InputGrabber;
import wob.city.console.logger.ConsoleLogger;
import wob.city.controller.TaskController;
import wob.city.food.abstraction.Food;
import wob.city.util.Calculation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<City> cities = new ArrayList<>();
    private static final List<Food> foods = new ArrayList<>();

    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        InputGrabber inputGrabber = new InputGrabber(foods, cities);

        taskController.loadFoods(foods);
        taskController.createCity("WoB City", Calculation.getRandomIntBetween(100, 1000), cities, foods);
        taskController.validateCitizens(cities);


        ConsoleLogger.getLogger().log("\n Application loaded successfully, type 'help' to see the commands available.");

        inputGrabber.start();
    }
}
