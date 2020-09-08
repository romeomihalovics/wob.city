package wob.city.services;

import wob.city.abstractions.Food;
import wob.city.logger.ConsoleLogger;
import wob.city.objects.Dairy;
import wob.city.objects.Grain;
import wob.city.objects.Meat;
import wob.city.objects.Vegetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FoodLoader {
    public void loadFoods(String fromFile, List<Food> foods) {
        ConsoleLogger.getLogger().log("\n Loading Foods");
        try (FileReader fileReader = new FileReader(fromFile); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String current;
            int i = 0;
            while ((current = bufferedReader.readLine()) != null) {
                if(i > 0) {
                   insertCurrent(Arrays.asList(current.split(";")), foods);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertCurrent(List<String> data, List<Food> foods) {
        switch (data.get(4)) {
            case "meat":
                foods.add(new Meat(data));
                break;
            case "vegetable":
                foods.add(new Vegetable(data));
                break;
            case "grain":
                foods.add(new Grain(data));
                break;
            default:
                foods.add(new Dairy(data));
                break;
        }
        ConsoleLogger.getLogger().log("Loaded: " + foods.get(foods.size() - 1).toString(100));
    }
}
