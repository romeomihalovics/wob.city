package wob.city.disaster.worker;

import wob.city.disaster.abstraction.Disaster;

import java.util.TimerTask;

public class SecondWave extends TimerTask {
    private final Disaster disaster;

    public SecondWave(Disaster disaster) {
        this.disaster = disaster;
    }

    @Override
    public void run() {
        disaster.secondWave();
    }
}
