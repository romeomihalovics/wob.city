package wob.city.abstractions;

import wob.city.logger.ActivityLogger;
import wob.city.objects.City;
import wob.city.util.Calculations;
import wob.city.util.Names;
import wob.city.worker.AgingWorker;
import wob.city.worker.DigestionWorker;
import wob.city.worker.EatingWorker;

import java.util.Timer;

public abstract class Person {
    public static final Names names = new Names();

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer weight;
    private Integer height;
    private final int normalMinHeight = 1;
    private final int normalMaxHeight = 200;
    private final int normalMinAge = 1;
    private final int normalMaxAge = 122;
    private int normalMinWeight;
    private int normalMaxWeight;
    private Timer timer;
    private DigestionWorker digestionWorker;
    private EatingWorker eatingWorker;
    private AgingWorker agingWorker;
    private City location;
    private int energy = 2500;
    private String lastFood = null;

    public Person(int normalMinWeight, int normalMaxWeight, String firstName, int weight){
        this.age = Calculations.getRandomIntBetween(18, 122);
        this.height = Calculations.getRandomIntBetween(1, 200);
        this.lastName = names.getLastName();
        this.firstName = firstName;
        this.weight = weight;
        this.normalMaxWeight = normalMaxWeight;
        this.normalMinWeight = normalMinWeight;
    }

    public Person(Person newAdult) {
        this.normalMaxWeight = newAdult.getNormalMaxWeight();
        this.normalMinWeight = newAdult.getNormalMinWeight();
        this.firstName = newAdult.getFirstName();
        this.lastName = newAdult.getLastName();
        this.weight = newAdult.getWeight();
        this.height = newAdult.getHeight();
        this.location = newAdult.getLocation();
        this.age = newAdult.getAge();
        this.energy = newAdult.getEnergy();
        this.lastFood = newAdult.getLastFood();
        this.timer = newAdult.getTimer();
        this.digestionWorker = newAdult.getDigestionWorker();
        this.eatingWorker = newAdult.getEatingWorker();
        this.agingWorker = newAdult.getAgingWorker();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getNormalMinHeight() {
        return normalMinHeight;
    }

    public int getNormalMaxHeight() {
        return normalMaxHeight;
    }

    public int getNormalMinWeight() {
        return normalMinWeight;
    }

    public void setNormalMinWeight(int normalMinWeight) {
        this.normalMinWeight = normalMinWeight;
    }

    public int getNormalMaxWeight() {
        return normalMaxWeight;
    }

    public void setNormalMaxWeight(int normalMaxWeight) {
        this.normalMaxWeight = normalMaxWeight;
    }

    public int getNormalMinAge() {
        return normalMinAge;
    }

    public int getNormalMaxAge() {
        return normalMaxAge;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public City getLocation() {
        return location;
    }

    public void setLocation(City city) {
        this.location = city;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getLastFood() {
        return lastFood;
    }

    public void setLastFood(String lastFood) {
        this.lastFood = lastFood;
    }

    public void setWorkers() {
        this.timer = new Timer();
        this.digestionWorker = new DigestionWorker(this);
        this.eatingWorker = new EatingWorker(this);
        this.agingWorker = new AgingWorker(this);

        this.timer.scheduleAtFixedRate(digestionWorker, (60*1000), (60*1000));
        this.timer.scheduleAtFixedRate(eatingWorker, (60*1000*3), (60*1000*3));
        this.timer.scheduleAtFixedRate(agingWorker, (60*1000*5), (60*1000*5));

    }

    public Timer getTimer() {
        return timer;
    }

    public DigestionWorker getDigestionWorker() {
        return digestionWorker;
    }

    public EatingWorker getEatingWorker() {
        return eatingWorker;
    }

    public AgingWorker getAgingWorker() {
        return agingWorker;
    }

    public void die() {
        this.digestionWorker.cancel();
        this.eatingWorker.cancel();
        this.agingWorker.cancel();
        this.timer.cancel();
        this.location.getPeople().remove(this);
        this.location.addDied(this);
        this.location.getDeathNews().addData(this);

        ActivityLogger.getLogger().log("\nPerson: " + this.getFullName() +
                " died at age " + this.getAge());
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n  type: '" + this.getClass().getSimpleName() + "'," +
                "\n  firstName: '" + firstName + "'," +
                "\n  lastName: '" + lastName + "'," +
                "\n  age: " + age + "," +
                "\n  weight: " + weight + "," +
                "\n  height: " + height + "," +
                "\n  location: '" + location.getName() + "'" +
                "\n  energy: '" + energy + "kcal'" +
                "\n  lastFood: '" + lastFood + "'" +
                "\n}";
    }
}
