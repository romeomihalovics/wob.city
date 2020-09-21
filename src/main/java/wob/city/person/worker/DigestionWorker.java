package wob.city.person.worker;

import wob.city.person.abstraction.Person;

import java.util.TimerTask;

public class DigestionWorker extends TimerTask {
    private final Person person;

    public DigestionWorker(Person person) {
        this.person = person;
    }

    @Override
    public void run(){
        person.doDigestion();
    }
}
