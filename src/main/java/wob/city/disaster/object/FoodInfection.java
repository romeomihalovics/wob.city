package wob.city.disaster.object;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Disaster;
import wob.city.food.object.Dairy;
import wob.city.food.object.Meat;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.DtoGenerator;


public class FoodInfection extends Disaster {
    public FoodInfection(String cause) {
        super("food_infection", "Food Infection", 0, cause);
    }

    @Override
    public void firstWave() {
        String event = "First Wave of Food Infection -> There was a food infection in the food factory, thus the produced amount of meat & dairy products will drop with 50%";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void secondWave() {
        getLocation().getCutOnFood().put(Dairy.class, 0.5);
        getLocation().getCutOnFood().put(Meat.class, 0.5);

        String event = "Second Wave of Food Infection -> production of meat & dairy products dropped with 50%";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
    }

    @Override
    public void thirdWave() {
        String event = "Third Wave of Food Infection -> The food infection has come to an end, the production of foods back to normal.";
        ConsoleLogger.getLogger().log(event);
        getLocation().removeCutOnFood();
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, location));
        getLocation().finishDisaster();
    }

    @Override
    public Timing getFirstWaveTiming() {
        return Timing.DEFAULT_DISASTER_FIRST_WAVE;
    }

    @Override
    public Timing getSecondWaveTiming() {
        return Timing.DEFAULT_DISASTER_SECOND_WAVE;
    }

    @Override
    public Timing getThirdWaveTiming() {
        return Timing.INFECTION_THIRD_WAVE;
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
