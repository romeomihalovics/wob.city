package wob.city.person.worker;

import wob.city.person.abstraction.Person;

import java.util.TimerTask;

public class AgingWorker extends TimerTask {
    private final Person person;

    public AgingWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.doAging();
    }
}
