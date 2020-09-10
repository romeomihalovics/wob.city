package wob.city.disaster.abstraction;

import wob.city.city.City;
import wob.city.disaster.worker.FirstWave;
import wob.city.disaster.worker.SecondWave;
import wob.city.disaster.worker.ThirdWave;
import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;

import java.util.*;

public abstract class Disaster {
    protected Timer timer;
    protected FirstWave firstWaveWorker;
    protected SecondWave secondWaveWorker;
    protected ThirdWave thirdWaveWorker;
    protected String id;
    protected String name;
    protected Integer killingRate;
    protected String cause;
    protected City location;
    protected List<Person> died = new ArrayList<>();
    protected Date date;

    public Disaster(String id, String name, Integer killingRate, String cause) {
        this.id = id;
        this.name = name;
        this.killingRate = killingRate;
        this.cause = cause;
        this.date = new Date();
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

    public void cancel() {
        this.firstWaveWorker.cancel();
        this.secondWaveWorker.cancel();
        this.thirdWaveWorker.cancel();
        this.timer.cancel();
    }

    public String getCause() {
        return cause;
    }

    public void start() {
        this.timer = new Timer();

        this.firstWaveWorker = new FirstWave(this);
        this.secondWaveWorker = new SecondWave(this);
        this.thirdWaveWorker = new ThirdWave(this);

        this.timer.schedule(firstWaveWorker, (60*1000));
        this.timer.schedule(secondWaveWorker, (60*1000*2));
        this.timer.schedule(thirdWaveWorker, (60*1000*3));
    }

    public void killPeople() {
        List<Person> people = Collections.synchronizedList(this.getLocation().getPeople());
        List<Person> toKill = new ArrayList<>();
        synchronized (people) {
            for (Person person : people) {
                if (Calculations.getRandomIntBetween(0, 100) <= this.killingRate) {
                    toKill.add(person);
                    this.died.add(person);
                }
            }
            toKill.forEach(Person::die);
        }
    }

    public List<Person> getDied() {
        return died;
    }

    public abstract void firstWave();
    public abstract void secondWave();
    public abstract void thirdWave();

    @Override
    public String toString() {
        return "\n{" +
                "\n  \"date\": \"" + date.toString() + "\"," +
                "\n  \"id\": \"" + id + "\"," +
                "\n  \"name\": \"" + name + "\"," +
                "\n  \"killingRate\": \"" + killingRate + "%\"," +
                "\n  \"cause\": \"" + cause + "\"," +
                "\n  \"died\":" + died.toString() +
                "\n}";
    }
}
