package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class Earthquake extends Disaster {
    public Earthquake(String cause) {
        super("earthquake", "Earthquake", 0.0, cause);
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
