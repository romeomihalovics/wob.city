package wob.city.controller;

import wob.city.abstractions.Food;
import wob.city.util.CommandUtils;

import java.util.List;


public class InputController {
    public void listFoods(List<Food> foods) {
        System.out.println(foods.toString());
    }

    public void getFood(List<Food> foods, String input) {
        Food food = CommandUtils.parseFoodName(foods, input);
        System.out.println((food != null) ? food.toString() : "Food not found");
    }

    public void getHelp() {
        System.out.println("\n -- COMMANDS --" +
                "\n  foods -- List all available food" +
                "\n  food -n name -- Get details about a specific food" +
                "\n  help -- List available commands" +
                "\n  exit -- Exit application");
    }

    public void exitApp(){
        System.out.println("Closing Application");
        System.exit(1);
    }
}
