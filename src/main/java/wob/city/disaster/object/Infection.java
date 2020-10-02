package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;

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
    public DeathCause getDeathCause() {
        return null;
    }
}
