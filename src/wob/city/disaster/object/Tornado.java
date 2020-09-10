package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class Tornado extends Disaster {
    public Tornado(String cause) {
        super("tornado", "Tornado", 0.0, cause);
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
