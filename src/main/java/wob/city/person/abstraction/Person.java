package wob.city.person.abstraction;

import wob.city.city.City;
import wob.city.console.logger.ActivityLogger;
import wob.city.database.dao.NewsPaperDao;
import wob.city.database.dao.PersonHistoryDao;
import wob.city.database.dto.ConsumptionNewsDto;
import wob.city.database.enums.PersonNewsCategory;
import wob.city.disaster.abstraction.Disaster;
import wob.city.family.Family;
import wob.city.food.abstraction.Food;
import wob.city.housing.abstraction.Housing;
import wob.city.person.enums.DeathCause;
import wob.city.person.enums.Profession;
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Type;
import wob.city.person.object.Man;
import wob.city.person.worker.*;
import wob.city.timing.Timing;
import wob.city.util.Calculation;
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
    protected KillingWorker killingWorker;
    protected CPRWorker cprWorker;
    protected City location;
    protected int energy = 2500;
    protected String lastFood = null;
    protected Family family = null;
    protected StatInFamily statInFamily = null;
    protected PersonHistoryDao personHistoryDao = new PersonHistoryDao();
    protected NewsPaperDao newsPaperDao = new NewsPaperDao();
    protected boolean criminal = false;
    protected List<Person> killedPeople = new ArrayList<>();
    protected Profession profession;
    protected int chanceOfBeingArrested = 0;
    protected boolean busy = false;

    public Person(int normalMinWeight, int normalMaxWeight, String firstName, int weight){
        this.age = Calculation.getRandomIntBetween(18, 122);
        this.height = Calculation.getRandomIntBetween(1, 200);
        this.lastName = NAMES.getLastName();
        this.firstName = firstName;
        this.weight = weight;
        this.normalMaxWeight = normalMaxWeight;
        this.normalMinWeight = normalMinWeight;
    }

    public Person(int normalMinWeight, int normalMaxWeight, int weight, int age, String firstName) {
        this.age = age;
        this.height = Calculation.getRandomIntBetween(1, 200);
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

        setProfession();
        startKillingIfCriminal();
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
        String event = "\n"+getType().getValue()+": " + getFullName() +
                " became " + age + " years old";
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

    public boolean isCriminal() {
        return criminal;
    }

    public List<Person> getKilledPeople() {
        return killedPeople;
    }

    public Profession getProfession() {
        return profession;
    }

    public abstract void setProfession();

    public int getChanceOfBeingArrested() {
        return chanceOfBeingArrested;
    }

    public void increaseChanceOfBeingArrested() {
        if(this.chanceOfBeingArrested + 5 <= 100) {
            this.chanceOfBeingArrested += 5;
        }
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setWorkers() {
        timer = new Timer();
        digestionWorker = new DigestionWorker(this);
        eatingWorker = new EatingWorker(this);
        agingWorker = new AgingWorker(this);

        timer.scheduleAtFixedRate(digestionWorker, Timing.DIGESTION.getValue(), Timing.DIGESTION.getValue());
        timer.scheduleAtFixedRate(eatingWorker, Timing.EATING.getValue(), Timing.EATING.getValue());
        timer.scheduleAtFixedRate(agingWorker, Timing.AGING.getValue(), Timing.AGING.getValue());
        startKillingIfCriminal();
    }

    private void startKillingIfCriminal() {
        if(criminal) {
            killingWorker = new KillingWorker(this);
            timer.scheduleAtFixedRate(killingWorker, Timing.CRIMINAL_ACTIVITY.getValue(), Timing.CRIMINAL_ACTIVITY.getValue());
        }
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

    public void recordAsDied(String event) {
        digestionWorker.cancel();
        eatingWorker.cancel();
        agingWorker.cancel();
        if(criminal) {
            killingWorker.cancel();
        }
        if(profession != null) {
            location.getProfessionals().get(profession.getValue()).remove(this);
        }
        timer.cancel();

        family.addDied(this);
        location.addDied(this);

        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.DEATH, this, null));
    }

    public void die(DeathCause deathCause) {
        setBusy(true);
        String event = "\n"+getType().getValue()+": " + getFullName() +
                " died at age " + getAge() + " (" + deathCause.getValue() + ")";
        location.callParamedic(this, deathCause, event);
    }

    public void die(DeathCause deathCause, Person criminal) {
        setBusy(true);
        String event = "\n"+getType().getValue()+": " + getFullName() +
                " died at age " + getAge() + " (" + deathCause.getValue() + " [" + criminal.getFullName() + "])";
        location.callParamedic(this, deathCause, event);
        if(deathCause == DeathCause.KILLED) {
            location.callPolice(criminal);
            newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.KILLED_BY_CRIMINAL, this, criminal));
        }
    }

    public void tryToKillSomeone() {
        if(!location.getPeople().isEmpty() && criminal){
            Person randomPerson = Calculation.getRandomNPeople(location.getPeople(), 1).get(0);
            killedPeople.add(randomPerson);
            increaseChanceOfBeingArrested();
            randomPerson.die(DeathCause.KILLED, this);
        }
    }

    public void tryToRevivePerson(Person toSave, DeathCause deathCause, String event) {
        if(profession == Profession.PARAMEDIC) {
            setBusy(true);
            cprWorker = new CPRWorker(this, toSave, deathCause, event, newsPaperDao);
            timer.schedule(cprWorker, Timing.PARAMEDIC_CPR.getValue());
        }
    }

    public void tryToCatchCriminal(Person criminal) {
        if(profession == Profession.POLICE) {
            setBusy(true);
            if (Calculation.getRandomIntBetween(0, 100) <= criminal.getChanceOfBeingArrested()) {
                String event = "Criminal (" + criminal.getFullName() + ") was caught by police (" + getFullName() + ") and shot to death";
                newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.CAUGHT_CRIMINAL, criminal, this));
                criminal.recordAsDied(event);
            } else {
                ActivityLogger.getLogger().log("A criminal ("+ criminal.getFullName() +") escaped from the police officer " + this.getFullName());
                newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.ESCAPED_CRIMINAL, criminal, this));
            }
            setBusy(false);
        }
    }

    public void tryToSaveHousing(Housing housing, List<Person> toKill, Disaster disaster) {
        if(profession == Profession.FIREFIGHTER) {
            setBusy(true);
            int chanceOfSuccess = 15;
            int attempts = 0;

            for(Family currentFamily : housing.getFamilies()) {

                tryToSaveFamily(chanceOfSuccess, currentFamily, disaster, toKill);

                attempts++;
                if(attempts == 3 ) {
                    attempts = 0;
                    chanceOfSuccess = Math.max(chanceOfSuccess - 10, 0);
                }
            }
            setBusy(false);
        }
    }

    private void tryToSaveFamily(int chanceOfSuccess, Family currentFamily, Disaster disaster, List<Person> toKill){
        if(Calculation.getRandomIntBetween(0, 100) > chanceOfSuccess) {
            toKill.addAll(currentFamily.getPeople());
            disaster.setDestroyedApartments(disaster.getDestroyedApartments() + 1);
            disaster.setDiedPeople(disaster.getDiedPeople() + currentFamily.getPeople().size());
            disaster.setDiedFamilies(disaster.getDiedFamilies() + 1);
        }else{
            currentFamily.getPeople().forEach(person -> {
                ActivityLogger.getLogger().log("A fire fighter ("+ this.getFullName() +") successfully saved a person ("+ person.getFullName() +")");
                newsPaperDao.uploadPersonNews(DtoGenerator.setupPersonNewsDto(PersonNewsCategory.SAVED_BY_FIREFIGHTER, person, this));
            });
        }
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n  \"type\": \"" + getType().getValue() + "\"," +
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

    public abstract Type getType();

    public abstract void doAging();

    public void doDigestion() {
        this.setEnergy(getEnergy() - 350);
        String event = "\n"+getType().getValue()+": " + getFullName() +
                " burned 350kcal, " + (this instanceof Man ? "his" : "her") +
                " energy levels changed from " +
                (getEnergy() + 350) + "kcal to " + getEnergy() + "kcal";
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        if(getEnergy() <= 0) {
            die(DeathCause.STARVED);
        }
    }

    public void doEating() {
        Food food = Calculation.getRandomFood(getLocation().getFoodRecipes());
        int amount = Calculation.getRandomIntBetween(0, 2500 - getEnergy());

        setLastFood(food.getName() + " " +
                Calculation.getAmountByEnergy(amount, food.getEnergy()) +
                "g -> " + amount + "kcal");

        setEnergy(getEnergy() + amount);

        String event = "\n"+getType().getValue()+": " + getFullName() + " ate " +
                Calculation.getAmountByEnergy(amount, food.getEnergy()) +
                "g (" + amount + "kcal) of " + food.getName() +
                " and " + (this instanceof Man ? "his" : "her") +
                " energy levels changed from " +
                (getEnergy() - amount) + "kcal to " + getEnergy() + "kcal";
        ActivityLogger.getLogger().log(event);
        personHistoryDao.uploadPersonHistory(DtoGenerator.setupPersonHistoryDto(event, this));
        newsPaperDao.uploadConsumptionNews(new ConsumptionNewsDto(location.getName(), food.getType().getValue(), Calculation.getAmountByEnergy(amount, food.getEnergy())));
    }
}
