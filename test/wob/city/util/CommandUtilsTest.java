package wob.city.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.objects.Meat;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandUtilsTest {
    @Test
    @DisplayName("Parsing food name from console input with regex should return '(Food Object [Meat]) Sausage' when input is 'food -n Sausage' or with '-gramm 100', but fake name should return null")
    public void parseFoodNameShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(CommandUtils.parseFoodName(foods, "food -n Sausage", false), foods.get(0));
        assertEquals(CommandUtils.parseFoodName(foods, "food -n Sausage -gramm 100", true), foods.get(0));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake", false));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake -gramm 100", true));
    }

    @Test
    @DisplayName("Get food object by name should return '(Food [Meat]) Sausage' when searched by 'Sausage', fake name should return null")
    public void getFoodShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(CommandUtils.getFood(foods, "Sausage"), foods.get(0));
        assertNull(CommandUtils.getFood(foods, "Fake"));
    }

    @Test
    @DisplayName("Get food gramm from console input should return '100' when input is 'food -n name -gramm 100'")
    public void parseFoodGrammShouldReturn() {
        assertEquals(CommandUtils.parseFoodGramm("food -n name -gramm 100"), 100);
    }
}
