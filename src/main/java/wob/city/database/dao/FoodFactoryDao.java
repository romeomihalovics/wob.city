package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.FoodAmountDto;
import wob.city.util.DtoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodFactoryDao implements Dao {

    public List<FoodAmountDto> fetchFoodAmounts(String cityName) {
        String query = "SELECT `id`, `food_name`, `amount`, `city` FROM `available_food` WHERE `city` = ?";

        List<Object> params = Collections.singletonList(cityName);

        return DtoGenerator.generateFoodAmountDto(runQuery(query,params));
    }

    public List<FoodAmountDto> fetchFoodAmounts(String cityName, String foodName) {
        String query = "SELECT `id`, `food_name`, `amount`, `city` FROM `available_food` WHERE `city` = ? AND `food_name` = ?";

        List<Object> params = new ArrayList<>();
        params.add(cityName);
        params.add(foodName);

        return DtoGenerator.generateFoodAmountDto(runQuery(query,params));
    }

    public void putOrAddFoodAmount(String cityName, String foodName, int amount) {
        List<FoodAmountDto> currentlyAvailable = fetchFoodAmounts(cityName, foodName);

        if(!currentlyAvailable.isEmpty()) {
            updateFoodAmount(cityName, foodName, amount);
        } else {
            insertFoodAmount(cityName, foodName, amount);
        }
    }

    private void updateFoodAmount(String cityName, String foodName, int amount) {
        String query = "UPDATE `available_food` SET `amount` = `amount` + ? WHERE `city` = ? AND `food_name` = ?";

        List<Object> params = new ArrayList<>();
        params.add(amount);
        params.add(cityName);
        params.add(foodName);

        runQuery(query, params);
    }

    private void insertFoodAmount(String cityName, String foodName, int amount) {
        String query = "INSERT INTO `available_food` (`city`, `food_name`, `amount`) VALUES (?, ?, ?)";

        List<Object> params = new ArrayList<>();
        params.add(cityName);
        params.add(foodName);
        params.add(amount);

        runQuery(query, params);
    }
}
