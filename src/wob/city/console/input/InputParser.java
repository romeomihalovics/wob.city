package wob.city.console.input;

import wob.city.food.abstraction.Food;
import wob.city.controller.InputController;
import wob.city.city.City;

import java.util.List;

public class InputParser {
    private final List<Food> foods;
    private final List<City> cities;

    private final InputController inputController;

    public InputParser(List<Food> foods, List<City> cities) {
        this.foods = foods;
        this.cities = cities;
        this.inputController = new InputController();
    }

    public void parseInput(String input){
        if(input.matches("^help$")){
            inputController.getHelp();
        }else if(input.matches("^cities$")){
            inputController.listCities(cities);
        }else if(input.matches("^disasters$")){
            inputController.listDisasters();
        }else if(input.matches("^disaster -c (.*) -n (.*)$")){
            inputController.startDisaster(cities, input);
        }else if(input.matches("^person -c (.*) -n (.*)$")){
            inputController.getPerson(cities, input);
        }else if(input.matches("^people -c (.*)$")){
            inputController.listPeople(cities, input);
        }else if(input.matches("^foods$")){
            inputController.listFoods(foods);
        }else if(input.matches("^food -n (.*) -gramm ([0-9]*)$")){
            inputController.getFoodByGramm(foods, input);
        }else if(input.matches("^food -n (.*)$")){
            inputController.getFood(foods, input);
        }else if(input.matches("^exit$")){
            inputController.exitApp();
        }
    }
}
