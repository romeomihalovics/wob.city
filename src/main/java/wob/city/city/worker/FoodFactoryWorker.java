package wob.city.city.worker;

import wob.city.city.City;
import wob.city.database.dao.FoodFactoryDao;
import wob.city.food.abstraction.Food;
import wob.city.util.Calculation;

import java.util.TimerTask;

public class FoodFactoryWorker extends TimerTask {
    private final City city;
    private final FoodFactoryDao foodFactoryDao;

    public FoodFactoryWorker(City city) {
        this.city = city;
        this.foodFactoryDao = new FoodFactoryDao();
    }

    @Override
    public void run() {
        goThroughRecipes();
    }

    private void goThroughRecipes() {
        for(Food food : city.getFoodRecipes()) {
            generateRandomAmountOfFood(food);
        }
    }

    private void generateRandomAmountOfFood(Food food) {
        double randomAmount = Calculation.getRandomIntBetween(30000, 80000);
        foodFactoryDao.putOrAddFoodAmount(city.getName(), food.getName(), randomAmount);
    }
}
