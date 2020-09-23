package wob.city.person.worker;

import wob.city.person.abstraction.Person;

import java.util.TimerTask;

public class KillingWorker extends TimerTask {
    private final Person person;

    public KillingWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        if(person.isCriminal()) {
            person.tryToKillSomeone();
        }
    }
}
