package wob.city;

import wob.city.abstractions.Food;
import wob.city.controller.InputController;
import wob.city.controller.TaskController;
import wob.city.input.InputGrabber;
import wob.city.objects.City;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<City> cities = new ArrayList<>();
    private static final List<Food> foods = new ArrayList<>();

    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        InputGrabber inputGrabber = new InputGrabber(foods);

        taskController.createCity("WoB City", Calculations.getRandomIntBetween(100, 1000), cities);
        taskController.validateCitizens(cities);
        taskController.loadFoods(foods);

        System.out.println("\n Application loaded successfully, type 'help' to see the commands available.");

        inputGrabber.start();
    }
}
