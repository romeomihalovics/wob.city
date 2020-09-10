package wob.city.disaster.object;

import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;

public class SubmarineEarthquakeFlood extends Disaster implements Consequence {

    public SubmarineEarthquakeFlood(String cause) {
        super("seflood", "Submarine Earthquake Flood", 0.0, cause);
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
