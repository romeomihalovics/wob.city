package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;

public class Infection extends Disaster {
    public Infection(String cause) {
        super("food_infection", "Food Infection", 0, cause);
    }

    @Override
    public void firstWave() {

    }

    @Override
    public void secondWave() {

    }

    @Override
    public void thirdWave() {

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