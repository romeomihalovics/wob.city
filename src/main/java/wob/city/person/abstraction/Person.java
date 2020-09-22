package wob.city.person.abstraction;

import wob.city.city.City;
import wob.city.console.logger.ActivityLogger;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.family.Family;
import wob.city.food.abstraction.Food;
import wob.city.person.enums.Professions;
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Types;
import wob.city.person.object.Man;
import wob.city.person.worker.AgingWorker;
import wob.city.person.worker.DigestionWorker;
import wob.city.person.worker.EatingWorker;
import wob.city.timing.Timings;
import wob.city.util.Calculations;
import wob.city.util.DtoGenerator;
import wob.city.util.Names;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public abstract class Person {
    public static final Names NAMES = new Names();
    public static final int ABSOLUTE_MAX_AGE = 122;

    protected String firstName;
    protected String lastName;
    protected Integer age;
    protected Integer weight;
    protected Integer height;
    protected final int normalMinHeight = 1;
    protected final int normalMaxHeight = 200;
    protected final int normalMinAge = 1;
    protected final int normalMaxAge = 122;
    protected int normalMinWeight;
    protected int normalMaxWeight;
    protected Timer timer;
    protected DigestionWorker digestionWorker;
    protected EatingWorker eatingWorker;
    protected AgingWorker agingWorker;
    protected City location;
    protected int energy = 2500;
    protected String lastFood = null;
    protected Family family = null;
    protected StatInFamily statInFamily = null;
    protected PersonHistoryDao personHistoryDao = new PersonHistoryDao();
    protected NewsPaperDao newsPaperDao = new NewsPaperDao();
    protected Boolean isCriminal = false;
    protected List<Person> killedPeople = new ArrayList<>();
    protected Professions profession;
    protected int chanceOfBeingArrested = 0;

    public Person(int normalMinWeight, int normalMaxWeight, String firstName, int weight){
        this.age = Calculations.getRandomIntBetween(18, 122);
        this.height = Calculations.getRandomIntBetween(1, 200);
        this.lastName = NAMES.getLastName();
        this.firstName = firstName;
        this.weight = weight;
        this.normalMaxWeight = normalMaxWeight;
        this.normalMinWeight = normalMinWeight;
    }

    public Person(int normalMinWeight, int normalMaxWeight, int weight, int age, String firstName) {
        this.age = age;
        this.height = Calculations.getRandomIntBetween(1, 200);
        this.lastName = NAMES.getLastName();
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
        this.family = newAdult.getFamily();
        this.statInFamily = newAdult.getStatInFamily();
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

    public void addAge() {
        this.age++;
        String event = "\n"+this.getType().getValue()+": " + this.getFullName() +
                " became " + this.age + " years old";
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public StatInFamily getStatInFamily() {
        return statInFamily;
    }

    public void setStatInFamily(StatInFamily statInFamily) {
        this.statInFamily = statInFamily;
    }

    public Boolean getCriminal() {
        return isCriminal;
    }

    public void setIsCriminal(Boolean isCriminal) {
        this.isCriminal = isCriminal;
    }

    public List<Person> getKilledPeople() {
        return killedPeople;
    }

    public void addKilledPerson(Person person) {
        this.killedPeople.add(person);
    }

    public Professions getProfession() {
        return profession;
    }

    public void setProfession(Professions profession) {
        this.profession = profession;
    }

    public int getChanceOfBeingArrested() {
        return chanceOfBeingArrested;
    }

    public void increaseChanceOfBeingArrested() {
        if(this.chanceOfBeingArrested + 5 <= 100) {
            this.chanceOfBeingArrested += 5;
        }
    }

    public void setWorkers() {
        this.timer = new Timer();
        this.digestionWorker = new DigestionWorker(this);
        this.eatingWorker = new EatingWorker(this);
        this.agingWorker = new AgingWorker(this);

        this.timer.scheduleAtFixedRate(digestionWorker, Timings.DIGESTION.getValue(), Timings.DIGESTION.getValue());
        this.timer.scheduleAtFixedRate(eatingWorker, Timings.EATING.getValue(), Timings.EATING.getValue());
        this.timer.scheduleAtFixedRate(agingWorker, Timings.AGING.getValue(), Timings.AGING.getValue());

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

        this.family.addDied(this);
        this.location.addDied(this);

        String event = "\n"+this.getType().getValue()+": " + this.getFullName() +
                " died at age " + this.getAge();
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        newsPaperDao.uploadPersonNews("death", DtoGenerator.setupPersonNewsDto(this));
    }



    @Override
    public String toString() {
        return "\n{" +
                "\n  \"type\": \"" + this.getType().getValue() + "\"," +
                "\n  \"firstName\": \"" + firstName + "\"," +
                "\n  \"lastName\": \"" + lastName + "\"," +
                "\n  \"age\": " + age + "," +
                "\n  \"weight\": " + weight + "," +
                "\n  \"height\": " + height + "," +
                "\n  \"location\": \"" + location.getName() + "\"," +
                "\n  \"energy\": \"" + energy + "kcal\"," +
                "\n  \"lastFood\": \"" + lastFood + "\"" +
                "\n}";
    }

    public abstract Types getType();

    public abstract void doAging();

    public void doDigestion() {
        this.setEnergy(this.getEnergy() - 350);
        String event = "\n"+this.getType().getValue()+": " + this.getFullName() +
                " burned 350kcal, " + (this instanceof Man ? "his" : "her") +
                " energy levels changed from " +
                (this.getEnergy() + 350) + "kcal to " + this.getEnergy() + "kcal";
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        if(this.getEnergy() <= 0) {
            this.die();
        }
    }

    public void doEating() {
        Food food = Calculations.getRandomFood(this.getLocation().getFoods());
        int amount = Calculations.getRandomIntBetween(0, 2500 - this.getEnergy());

        this.setLastFood(food.getName() + " " +
                Calculations.getAmountByEnergy(amount, food.getEnergy()) +
                "g -> " + amount + "kcal");

        this.setEnergy(this.getEnergy() + amount);

        String event = "\n"+this.getType().getValue()+": " + this.getFullName() + " ate " +
                Calculations.getAmountByEnergy(amount, food.getEnergy()) +
                "g (" + amount + "kcal) of " + food.getName() +
                " and " + (this instanceof Man ? "his" : "her") +
                " energy levels changed from " +
                (this.getEnergy() - amount) + "kcal to " + this.getEnergy() + "kcal";
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        newsPaperDao.uploadConsumptionNews(new ConsumptionNewsDto(this.location.getName(), food.getType().getValue(), Calculations.getAmountByEnergy(amount, food.getEnergy())));
    }
}
