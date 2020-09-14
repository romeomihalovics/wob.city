package wob.city.city;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;
import wob.city.family.Family;
import wob.city.food.abstraction.Food;
import wob.city.housing.abstraction.Housing;
import wob.city.housing.object.BrickBlock;
import wob.city.housing.object.FamilyHouse;
import wob.city.housing.object.PanelBlock;
import wob.city.newspaper.object.ConsumptionNews;
import wob.city.newspaper.object.DeathNews;
import wob.city.newspaper.object.DisasterNews;
import wob.city.newspaper.object.NewBornNews;
import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;
import wob.city.city.worker.NewBornWorker;

import java.util.*;

public class City {
    private String name;
    private final List<Family> families;
    private final List<Housing> houses;
    private final List<Person> people;
    private final List<Food> foods;
    private final List<Person> died = Collections.synchronizedList(new ArrayList<>());
    private Timer timer;
    private NewBornWorker newBornWorker;
    private final ConsumptionNews consumptionNews;
    private final DeathNews deathNews;
    private final NewBornNews newBornNews;
    private Disaster disaster = null;
    private final DisasterNews disasterNews;

    public City(String name, List<Person> people, List<Food> foods) {
        this.name = name;
        this.families = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.people = people;
        this.foods = foods;
        this.consumptionNews = new ConsumptionNews();
        this.deathNews = new DeathNews();
        this.newBornNews = new NewBornNews();
        this.disasterNews = new DisasterNews();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public List<Housing> getHouses() {
        return houses;
    }

    public void addHousing(Housing housing) {
        this.houses.add(housing);
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addDied(Person person) {
        synchronized (people) {
            people.remove(person);
        }
        this.died.add(person);
    }

    public List<Person> getDied() {
        return died;
    }

    public void setWorkers() {
        this.timer = new Timer();
        this.newBornWorker = new NewBornWorker(this);

        this.timer.scheduleAtFixedRate(newBornWorker, (60*1000*2), (60*1000*2));
    }

    public ConsumptionNews getConsumptionNews() {
        return consumptionNews;
    }

    public DeathNews getDeathNews() {
        return deathNews;
    }

    public NewBornNews getNewBornNews() {
        return newBornNews;
    }

    public Disaster getDisaster() {
        return disaster;
    }

    public void startDisaster(Disaster disaster) {
        if(this.disaster == null && !(disaster instanceof Consequence)) {
            this.disaster = disaster;
            this.disaster.start();
            ConsoleLogger.getLogger().log("A natural disaster '"+disaster.getName()+"' caused by '"+disaster.getCause()+"' started happening in city '"+this.getName()+"'");
        }else{
            ConsoleLogger.getLogger().log("A disaster is already happening in city '"+this.getName()+"'");
        }
    }

    public void finishDisaster() {
        ConsoleLogger.getLogger().log("The disaster ("+disaster.getName()+") is ended in city '"+this.getName()+"' with "+this.disaster.getDiedPeople()+" deaths");
        this.disasterNews.addData(disaster);
        this.disasterNews.manualPublish();
        this.disaster.cancel();
        this.disaster = null;
    }

    public void continueDisaster(Disaster disaster) {
        if(disaster instanceof Consequence && this.disaster != null) {
            this.disasterNews.addData(this.disaster);
            this.disaster.cancel();
            ConsoleLogger.getLogger().log("A natural disaster ("+this.disaster.getName()+" with "+this.disaster.getDiedPeople()+" deaths) is being followed up with another disaster '"+disaster.getName()+"' in city '"+this.getName()+"'");
            this.disaster = disaster;
            this.disaster.setLocation(this);
            this.disaster.start();
        }
    }

    public void createFamilies() {
        List<Person> orphans = new ArrayList<>();
        people.forEach(person -> {
            List<Family> shuffledFamilies = new LinkedList<>(families);
            Collections.shuffle(shuffledFamilies);
            shuffledFamilies.forEach(family -> family.tryToAdd(person));
            if(person.getFamily() == null && person.getAge() >= 18) {
                Family family = new Family(this, person);
                family.findHousing();
                families.add(family);
                person.setFamily(family);
                person.setStatInFamily("Parent");
            }else if(person.getFamily() == null && person.getAge() < 18){
                orphans.add(person);
            }
        });

        tryToFindFamily(orphans);
    }

    private void tryToFindFamily(List<Person> orphans) {
        List<Person> leftToDie = new ArrayList<>();
        orphans.forEach(person -> {
            List<Family> shuffledFamilies = new LinkedList<>(families);
            Collections.shuffle(shuffledFamilies);
            shuffledFamilies.forEach(family -> family.tryToAdd(person));
            if(person.getFamily() == null) {
                leftToDie.add(person);
            }
        });
        removeOrphans(leftToDie);
    }

    private void removeOrphans(List<Person> orphans) {
        synchronized (people) {
            people.removeAll(orphans);
        }
    }

    public Family getFertileFamily(){
        List<Family> family = new ArrayList<>();
        List<Family> shuffledFamilies = new LinkedList<>(families);
        Collections.shuffle(shuffledFamilies);
        for (Family f : shuffledFamilies) {
            List<Person> parents = new ArrayList<>();
            f.getPeople().forEach(person -> {
                if ((person.getType().equals("Man") || person.getType().equals("Woman")) &&
                        person.getStatInFamily().equals("Parent") && person.getAge() >= 20 && person.getAge() <= 40) {
                    parents.add(person);
                }
            });
            if (parents.size() == 2) {
                family.add(f);
                break;
            }
        }
        return (family.size() > 0) ? family.get(0) : null;
    }

    public Housing buildHouse() {
        Housing housing = null;
        switch (Calculations.getRandomIntBetween(1,3)) {
            case 1:
                housing = new BrickBlock();
                break;
            case 2:
                housing = new PanelBlock();
                break;
            default:
                housing = new FamilyHouse();
                break;
        }
        addHousing(housing);
        return housing;
    }

    @Override
    public synchronized String toString() {
        return "\n City: {" +
                "\n  name: '" + name + "'," +
                "\n  population: " + people.size() + "," +
                "\n  died: " + died.size() + "," +
                "\n  people: {" +
                "\n     kids: {" +
                "\n         girls: " + Calculations.getPeopleCountByType(people, "Girl") +
                "\n         boys: " + Calculations.getPeopleCountByType(people, "Boy") +
                "\n     }" +
                "\n     Woman: " + Calculations.getPeopleCountByType(people, "Woman") +
                "\n     Man: " + Calculations.getPeopleCountByType(people, "Man") +
                "\n  }, " +
                "\n  families: " + families.size() +
                "\n }";
    }
}
