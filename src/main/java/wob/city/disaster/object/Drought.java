package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;

public class Drought extends Disaster {
    public Drought(String cause) {
        super("drought", "Drought", 0, cause);
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
