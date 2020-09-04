package wob.city.input;

import wob.city.abstractions.Food;
import wob.city.controller.InputController;

import java.util.List;

public class InputParser {
    private final List<Food> foods;

    private final InputController inputController;

    public InputParser(List<Food> foods) {
        this.foods = foods;
        this.inputController = new InputController();
    }

    public void parseInput(String input){
        if(input.matches("^help$")){
            inputController.getHelp();
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
