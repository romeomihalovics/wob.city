package wob.city.controller;

import wob.city.abstractions.Food;
import wob.city.util.CommandUtils;

import java.util.List;
import java.util.stream.Collectors;


public class InputController {
    public void listFoods(List<Food> foods) {
        System.out.println("\n" + foods.stream().map(food -> food.toString(100)).collect(Collectors.joining()));
    }

    public void getFood(List<Food> foods, String input) {
        Food food = CommandUtils.parseFoodName(foods, input, false);
        System.out.println((food != null) ? food.toString(100) : "Food not found");
    }

    public void getFoodByGramm(List<Food> foods, String input) {
        Food food = CommandUtils.parseFoodName(foods, input, true);
        Integer gramm = CommandUtils.parseFoodGramm(input);
        System.out.println((food != null) ? food.toString(gramm) : "Food not found");
    }

    public void getHelp() {
        System.out.println("\n -- COMMANDS --" +
                "\n  foods -- List all available food" +
                "\n  food -n name -- Get details about a specific food" +
                "\n  food -n name -gramm 100 -- Get details about a specific food, with a specific weight" +
                "\n  help -- List available commands" +
                "\n  exit -- Exit application");
    }

    public void exitApp(){
        System.out.println("Closing Application");
        System.exit(1);
    }
}
