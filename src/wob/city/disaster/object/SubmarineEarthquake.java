package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class SubmarineEarthquake extends Disaster {
    public SubmarineEarthquake(String cause) {
        super("searthquake", "Submarine Earthquake", 0.0, cause);
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
