package wob.city.console.input;

import wob.city.food.abstraction.Food;
import wob.city.city.City;

import java.util.List;
import java.util.Scanner;

public class InputGrabber {
    private final InputParser inputParser;
    
    public InputGrabber(List<Food> foods, List<City> cities) {
        this.inputParser = new InputParser(foods, cities);
    }
    
    public void start() {
        String input;
        while((input = grabInput()) != null){
            inputParser.parseInput(input);
        }
    }

    private String grabInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Command: ");
        return scanner.nextLine();
    }
}
