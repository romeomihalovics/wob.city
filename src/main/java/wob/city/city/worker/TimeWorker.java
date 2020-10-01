package wob.city.city.worker;

import wob.city.city.City;

import java.util.TimerTask;

public class TimeWorker extends TimerTask {
    private final City city;

    public TimeWorker(City city) {
        this.city = city;
    }

    @Override
    public void run() {
        city.addHour();
    }
}
