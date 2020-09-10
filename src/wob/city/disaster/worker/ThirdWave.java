package wob.city.disaster.worker;

import wob.city.disaster.abstraction.Disaster;

import java.util.TimerTask;

public class ThirdWave extends TimerTask {
    private final Disaster disaster;

    public ThirdWave(Disaster disaster) {
        this.disaster = disaster;
    }

    @Override
    public void run() {
        disaster.thirdWave();
    }
}