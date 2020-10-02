package wob.city.city.worker;

import wob.city.city.City;
import wob.city.city.enums.FoodFactory;
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
        double cutOnFood = checkIfFoodOnCutList(food);
        double randomAmount = Calculation.getRandomIntBetween((int) (FoodFactory.GENERATE_MIN_AMOUNT.getValue() * cutOnFood), (int) (FoodFactory.GENERATE_MAX_AMOUNT.getValue() * cutOnFood));
        foodFactoryDao.putOrAddFoodAmount(city.getName(), food.getName(), randomAmount);
    }

    private double checkIfFoodOnCutList(Food food) {
        double percentage = 1;
        if(city.getCutOnFood().containsKey(food.getClass())) {
            percentage = city.getCutOnFood().get(food.getClass());
        }
        return Math.max(percentage, 0);
    }
}
