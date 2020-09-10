package wob.city.disaster.object;

import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;

public class Aftershock extends Disaster implements Consequence {
    public Aftershock(String cause) {
        super("aftershock", "Aftershock", 0.0, cause);
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
}
