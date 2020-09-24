package wob.city.disaster.abstraction;

import wob.city.city.City;
import wob.city.database.dao.DisasterHistoryDao;
import wob.city.disaster.worker.FirstWave;
import wob.city.disaster.worker.SecondWave;
import wob.city.disaster.worker.ThirdWave;
import wob.city.housing.abstraction.Housing;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.DeathCause;
import wob.city.timing.Timing;
import wob.city.util.Calculation;

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
    protected List<Housing> destroyed = new ArrayList<>();
    protected int diedFamilies = 0;
    protected int diedPeople = 0;
    protected Date date;
    protected DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();

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
        firstWaveWorker.cancel();
        secondWaveWorker.cancel();
        thirdWaveWorker.cancel();
        timer.cancel();
    }

    public String getCause() {
        return cause;
    }

    public void start() {
        timer = new Timer();

        firstWaveWorker = new FirstWave(this);
        secondWaveWorker = new SecondWave(this);
        thirdWaveWorker = new ThirdWave(this);

        timer.schedule(firstWaveWorker, Timing.DISASTER_FIRST_WAVE.getValue());
        timer.schedule(secondWaveWorker, Timing.DISASTER_SECOND_WAVE.getValue());
        timer.schedule(thirdWaveWorker, Timing.DISASTER_THIRD_WAVE.getValue());
    }

    public void killPeople() {
        List<Person> people = Collections.synchronizedList(this.getLocation().getPeople());
        List<Housing> housings = Collections.synchronizedList(this.getLocation().getHouses());
        List<Person> toKill = new ArrayList<>();
        synchronized (housings) {
            for(Housing housing : housings) {
                if(Calculation.getRandomIntBetween(0, 100) <= this.killingRate) {
                    housing.getFamilies().forEach(family -> {
                        toKill.addAll(family.getPeople());
                        destroyed.add(housing);
                        diedPeople += family.getPeople().size();
                    });
                    diedFamilies += housing.getFamilies().size();
                }
            }
        }
        synchronized (people) {
            toKill.forEach(person -> person.die(getDeathCause()));
        }
    }

    public List<Housing> getDestroyed() {
        return destroyed;
    }

    public int getDiedPeople() {
        return  diedPeople;
    }

    public int getDiedFamilies() {
        return diedFamilies;
    }

    public abstract void firstWave();
    public abstract void secondWave();
    public abstract void thirdWave();
    public abstract DeathCause getDeathCause();

    @Override
    public String toString() {
        return "\n{" +
                "\n  \"date\": \"" + date.toString() + "\"," +
                "\n  \"id\": \"" + id + "\"," +
                "\n  \"name\": \"" + name + "\"," +
                "\n  \"killingRate\": \"" + killingRate + "%\"," +
                "\n  \"cause\": \"" + cause + "\"," +
                "\n  \"destroyedBuildings\":" + destroyed.size() + "," +
                "\n \"diedFamilies\": " + diedFamilies + "," +
                "\n \"diedPeople\": " + diedPeople +
                "\n}";
    }
}
