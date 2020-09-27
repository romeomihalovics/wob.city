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
    protected Integer destroyedApartments = 0;
    protected Integer diedFamilies = 0;
    protected Integer diedPeople = 0;
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
        List<Person> toKill = new ArrayList<>();
        destroyHousings(toKill);
        killVictims(toKill);
    }

    private void destroyHousings(List<Person> toKill) {
        List<Housing> housings = Collections.synchronizedList(this.getLocation().getHouses());
        synchronized (housings) {
            for(Housing housing : housings) {
                if(Calculation.getRandomIntBetween(0, 100) <= this.killingRate) {
                    location.callFireFighter(housing, toKill, this);
                }
            }
        }
    }

    private void killVictims(List<Person> toKill) {
        List<Person> people = Collections.synchronizedList(this.getLocation().getPeople());
        synchronized (people) {
            toKill.forEach(person -> person.die(getDeathCause()));
        }
    }

    public Integer getDestroyedApartments() {
        return destroyedApartments;
    }

    public void setDestroyedApartments(Integer destroyedApartments) {
        this.destroyedApartments = destroyedApartments;
    }

    public Integer getDiedPeople() {
        return  diedPeople;
    }

    public Integer getDiedFamilies() {
        return diedFamilies;
    }

    public void setDiedFamilies(Integer diedFamilies) {
        this.diedFamilies = diedFamilies;
    }

    public void setDiedPeople(Integer diedPeople) {
        this.diedPeople = diedPeople;
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
                "\n  \"destroyedApartments\":" + destroyedApartments + "," +
                "\n \"diedFamilies\": " + diedFamilies + "," +
                "\n \"diedPeople\": " + diedPeople +
                "\n}";
    }
}
