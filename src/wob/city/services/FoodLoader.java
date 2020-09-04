package wob.city.services;

import wob.city.Main;
import wob.city.objects.Dairy;
import wob.city.objects.Grain;
import wob.city.objects.Meat;
import wob.city.objects.Vegetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FoodLoader {
    public static void loadFoods(String fromFile) {
        System.out.println("\n Loading Foods");
        try (FileReader fileReader = new FileReader(fromFile); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String current;
            int i = 0;
            while ((current = bufferedReader.readLine()) != null) {
                if(i > 0) {
                   insertCurrent(current.split(";"));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertCurrent(String[] data) {
        switch (data[4]) {
            case "meat":
                Main.foods.add(new Meat(data));
                break;
            case "vegetable":
                Main.foods.add(new Vegetable(data));
                break;
            case "grain":
                Main.foods.add(new Grain(data));
                break;
            default:
                Main.foods.add(new Dairy(data));
                break;
        }
        System.out.println("Loaded: " + Main.foods.get(Main.foods.size() - 1).toString());
    }
}
