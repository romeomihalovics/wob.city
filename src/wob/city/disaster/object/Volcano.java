package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class Volcano extends Disaster {
    public Volcano(String cause) {
        super("volcano", "Volcanic Eruption", 0.0, cause);
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
