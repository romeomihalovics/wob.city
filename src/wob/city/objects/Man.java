package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

public class Man extends Person {
    public Man(){
        super(65, 120);
        this.setFirstName(names.getMaleName());
        this.setWeight(Calculations.getRandomIntBetween(65, 120));
    }

    public Man(Boy newAdult){
        super(65,120);
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
