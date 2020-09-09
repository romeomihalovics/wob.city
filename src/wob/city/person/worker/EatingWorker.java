package wob.city.person.worker;

import wob.city.person.abstraction.Person;

import java.util.TimerTask;

public class EatingWorker extends TimerTask {
    private final Person person;

    public EatingWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.doEating();
    }
}
