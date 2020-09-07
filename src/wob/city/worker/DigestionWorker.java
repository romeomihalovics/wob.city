package wob.city.worker;

import wob.city.abstractions.Person;

import java.util.TimerTask;

public class DigestionWorker extends TimerTask {
    private final Person person;

    public DigestionWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run(){
        person.setEnergy(person.getEnergy() - 350);
        if(person.getEnergy() <= 0) {
            person.die();
        }
    }
}
