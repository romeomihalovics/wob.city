package wob.city.disaster.object;

import wob.city.disaster.abstraction.Disaster;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;

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
        getLocation().finishDisaster();
    }

    @Override
    public Timing getFirstWaveTiming() {
        return Timing.DEFAULT_DISASTER_FIRST_WAVE;
    }

    @Override
    public Timing getSecondWaveTiming() {
        return Timing.DEFAULT_DISASTER_SECOND_WAVE;
    }

    @Override
    public Timing getThirdWaveTiming() {
        return Timing.DROUGHT_THIRD_WAVE;
    }

    @Override
    public DeathCause getDeathCause() {
        return null;
    }
}
