package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class Woman extends Person {
    public Woman(){
        super(50,65);
        this.setFirstName(names.getFemaleName());
        this.setWeight(Calculations.getRandomIntBetween(50, 65));
    }

    public Woman(Girl newAdult) {
        super(50, 65);
        this.setFirstName(newAdult.getFirstName());
        this.setLastName(newAdult.getLastName());
        this.setWeight(newAdult.getWeight());
        this.setHeight(newAdult.getHeight());
        this.setLocation(newAdult.getLocation());
        this.setAge(newAdult.getAge());
        this.setEnergy(newAdult.getEnergy());
        this.setLastFood(newAdult.getLastFood());
        this.setTimer(newAdult.getTimer());
        this.setDigestionWorker(newAdult.getDigestionWorker());
        this.setEatingWorker(newAdult.getEatingWorker());
        this.setAgingWorker(newAdult.getAgingWorker());
    }
}
