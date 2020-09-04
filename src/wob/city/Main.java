package wob.city;

import wob.city.abstractions.Food;
import wob.city.controller.TaskController;
import wob.city.objects.City;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final List<City> cities = new ArrayList<>();
    public static final List<Food> foods = new ArrayList<>();

    public static void main(String[] args) {
        TaskController.createCity("WoB City", Calculations.getRandomIntBetween(100, 1000));
        TaskController.validateCitizens(cities);
        TaskController.loadFoods();
    }
}
