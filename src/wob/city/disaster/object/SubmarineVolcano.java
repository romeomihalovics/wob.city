package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;

public class SubmarineVolcano extends Disaster {
    public SubmarineVolcano(String cause) {
        super("svolcano", "Submarine Volcanic Eruption", 0.0, cause);
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
