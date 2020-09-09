package wob.city.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.controller.services.FoodLoader;
import wob.city.food.abstraction.Food;
import wob.city.food.object.Meat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodLoaderTest {
    @Test
    @DisplayName("When food type is meat, a new meat object should be inserted to the foods list")
    void insertCurrentShouldAddToList() {
        FoodLoader foodLoader = new FoodLoader();
        List<String> expectedFoodDetail = Arrays.asList("Sausage", "14", "1", "28", "meat");
        List<Food> foodsExpected = Collections.singletonList(new Meat(expectedFoodDetail));
        List<Food> foods = new ArrayList<>();

        foodLoader.insertCurrent(expectedFoodDetail, foods);

        assertEquals(foodsExpected.stream().map(food -> food.toString(100)).collect(Collectors.joining()),
                foods.stream().map(food -> food.toString(100)).collect(Collectors.joining()));
    }
}
