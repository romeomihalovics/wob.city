package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class Flood extends Disaster {

    public Flood(String cause) {
        super("flood", "Flood", 0.0, cause);
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
