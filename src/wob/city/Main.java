package wob.city;

import wob.city.controller.TaskController;
import wob.city.objects.City;
import wob.city.util.Calculations;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
        TaskController taskController = new TaskController();

        taskController.createCity("WoB City", Calculations.getRandomIntBetween(100, 1000));
        System.out.print(cities.get(0).toString());
    }
}
