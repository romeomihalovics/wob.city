package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.FoodAmountDto;
import wob.city.database.dto.QueryDto;
import wob.city.util.DtoGenerator;

import java.util.List;

public class FoodFactoryDao implements Dao {

    public List<FoodAmountDto> fetchFoodAmounts(String cityName, String foodName) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("SELECT `id`, `food_name`, `amount`, `city` FROM `available_food` WHERE `city` = ? AND `food_name` = ?");

        queryDto.addParam(cityName);
        queryDto.addParam(foodName);

        return DtoGenerator.generateFoodAmountDto(runQuery(queryDto));
    }

    public void putOrAddFoodAmount(String cityName, String foodName, double amount) {
        List<FoodAmountDto> currentlyAvailable = fetchFoodAmounts(cityName, foodName);

        if(!currentlyAvailable.isEmpty()) {
            updateFoodAmount(cityName, foodName, amount);
        } else {
            insertFoodAmount(cityName, foodName, amount);
        }
    }

    private void updateFoodAmount(String cityName, String foodName, double amount) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("UPDATE `available_food` SET `amount` = `amount` + ? WHERE `city` = ? AND `food_name` = ?");

        queryDto.addParam(amount);
        queryDto.addParam(cityName);
        queryDto.addParam(foodName);

        runQuery(queryDto);
    }

    private void insertFoodAmount(String cityName, String foodName, double amount) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `available_food` (`city`, `food_name`, `amount`) VALUES (?, ?, ?)");

        queryDto.addParam(cityName);
        queryDto.addParam(foodName);
        queryDto.addParam(amount);

        runQuery(queryDto);
    }
}
