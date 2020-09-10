package wob.city.disaster.worker;

import wob.city.disaster.abstraction.Disaster;

import java.util.TimerTask;

public class FirstWave extends TimerTask {
    private final Disaster disaster;

    public FirstWave(Disaster disaster) {
        this.disaster = disaster;
    }

    @Override
    public void run() {
        disaster.firstWave();
    }
}
