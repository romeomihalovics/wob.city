package wob.city.worker;

import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.logger.ActivityLogger;
import wob.city.util.Calculations;

import java.util.List;
import java.util.TimerTask;

public class EatingWorker extends TimerTask {
    private final Person person;

    public EatingWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        List<Food> foods = person.getLocation().getFoods();
        Food food = foods.get(Calculations.getRandomIntBetween(0, foods.size() - 1));
        int amount = Calculations.getRandomIntBetween(0, 2500 - person.getEnergy());

        person.setLastFood(food.getName() + " " + Calculations.round(((double) amount/food.getEnergy())*100, 2) + "g -> " + amount + "kcal");
        person.setEnergy(person.getEnergy() + amount);

        ActivityLogger.getLogger().log("\nPerson: " + person.getFullName() + " ate " +
                Calculations.round(((double) amount/food.getEnergy())*100, 2) +
                "g (" + amount + "kcal) of " + food.getName() +
                " and his/her energy levels changed from " +
                (person.getEnergy() - amount) + "kcal to " + person.getEnergy() + "kcal");

    }
}
