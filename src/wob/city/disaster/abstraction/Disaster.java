package wob.city.disaster.abstraction;

import wob.city.city.City;

public abstract class Disaster {
    protected String id;
    protected String name;
    protected Double killingRate;
    protected String cause;
    protected City location;

    public Disaster(String id, String name, Double killingRate, String cause) {
        this.id = id;
        this.name = name;
        this.killingRate = killingRate;
        this.cause = cause;
    }

    public void setLocation(City city) {
        this.location = city;
    }

    public City getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCause() {
        return cause;
    }

    public abstract void firstWave();
    public abstract void secondWave();
    public abstract void thirdWave();
}
