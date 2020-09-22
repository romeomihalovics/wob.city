package wob.city.city;

import wob.city.city.worker.NewBornWorker;
import wob.city.console.logger.ConsoleLogger;
import wob.city.database.dao.DisasterHistoryDao;
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
import wob.city.person.enums.StatInFamily;
import wob.city.person.enums.Types;
import wob.city.timing.Timings;
import wob.city.util.Calculations;
import wob.city.util.DtoGenerator;

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
    private final List<Disaster> disaster = Collections.synchronizedList(new ArrayList<>());
    private final DisasterNews disasterNews;
    private final DisasterHistoryDao disasterHistoryDao = new DisasterHistoryDao();
    private final HashMap<String, List<Person>> professionals = new HashMap<>();
    private final List<Person> criminals = new ArrayList<>();

    public City(String name, List<Person> people, List<Food> foods) {
        this.name = name;
        this.families = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.people = people;
        this.foods = foods;
        this.consumptionNews = new ConsumptionNews(this);
        this.deathNews = new DeathNews(this);
        this.newBornNews = new NewBornNews(this);
        this.disasterNews = new DisasterNews(this);

        this.professionals.put("Ambulance", new ArrayList<>());
        this.professionals.put("Police", new ArrayList<>());
        this.professionals.put("FireFighter", new ArrayList<>());
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

        this.timer.scheduleAtFixedRate(newBornWorker, Timings.NEW_BORN_WORKER.getValue(), Timings.NEW_BORN_WORKER.getValue());
    }

    public List<Disaster> getDisaster() {
        return disaster;
    }

    public Map<String, List<Person>> getProfessionals() {
        return professionals;
    }

    public List<Person> getCriminals() {
        return criminals;
    }

    public void startDisaster(Disaster disaster) {
        synchronized (this.disaster) {
            if (this.disaster.isEmpty() && !(disaster instanceof Consequence)) {
                this.disaster.add(disaster);
                this.disaster.get(0).start();

                String event = "A natural disaster '" + disaster.getName() + "' caused by '" + disaster.getCause() + "' started happening in city '" + this.getName() + "'";
                ConsoleLogger.getLogger().log(event);

                disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));
            } else {
                ConsoleLogger.getLogger().log("A disaster is already happening in city '" + this.getName() + "'");
            }
        }
    }

    public void finishDisaster() {
        String event = "The disaster ("+disaster.get(0).getName()+") is ended in city '"+this.getName()+"' with "+this.disaster.get(0).getDiedPeople()+" deaths";
        ConsoleLogger.getLogger().log(event);
        disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));
        this.disasterNews.manualPublish();
        this.disaster.get(0).cancel();
        this.disaster.remove(0);
    }

    public void continueDisaster(Disaster disaster) {
        if(disaster instanceof Consequence && !this.disaster.isEmpty()) {
            this.disaster.get(0).cancel();
            String event = "A natural disaster ("+this.disaster.get(0).getName()+" with "+this.disaster.get(0).getDiedPeople()+" deaths) is being followed up with another disaster '"+disaster.getName()+"' in city '"+this.getName()+"'";
            ConsoleLogger.getLogger().log(event);

            disasterHistoryDao.uploadDisasterHistory(DtoGenerator.setupDisasterHistoryDto(event, this));

            this.disaster.remove(0);
            this.disaster.add(disaster);
            this.disaster.get(0).setLocation(this);
            this.disaster.get(0).start();
        }
    }

    @SuppressWarnings("unchecked")
    public void createFamilies() {
        List<Person> orphans = new ArrayList<>();
        people.forEach(person -> {
            List<Family> shuffledFamilies = (List<Family>) Calculations.shuffleList(families);
            shuffledFamilies.forEach(family -> family.tryToAdd(person, false));
            if(person.getFamily() == null && person.getAge() >= 18) {
                Family family = new Family(this, person);
                family.findHousing();
                families.add(family);
                person.setFamily(family);
                person.setStatInFamily(StatInFamily.PARENT);
            }else if(person.getFamily() == null && person.getAge() < 18){
                orphans.add(person);
            }
        });

        tryToFindFamily(orphans);
    }

    @SuppressWarnings("unchecked")
    private void tryToFindFamily(List<Person> orphans) {
        List<Person> leftToDie = new ArrayList<>();
        orphans.forEach(person -> {
            List<Family> shuffledFamilies = (List<Family>) Calculations.shuffleList(families);
            shuffledFamilies.forEach(family -> family.tryToAdd(person, false));
            if(person.getFamily() == null) {
                leftToDie.add(person);
            }
        });
        removeOrphans(leftToDie);
    }

    private void removeOrphans(List<Person> orphans) {
        synchronized (people) {
            orphans.forEach(orphan -> {
                orphan.getAgingWorker().cancel();
                orphan.getDigestionWorker().cancel();
                orphan.getEatingWorker().cancel();
                orphan.getTimer().cancel();
            });
            people.removeAll(orphans);
        }
    }

    @SuppressWarnings("unchecked")
    public Family getFertileFamily(){
        List<Family> family = new ArrayList<>();
        List<Family> shuffledFamilies = (List<Family>) Calculations.shuffleList(families);
        for (Family f : shuffledFamilies) {
            List<Person> parents = new ArrayList<>();
            List<Person> children = new ArrayList<>();
            f.getPeople().forEach(person -> {
                if ((person.getType() == Types.MAN || person.getType() == Types.WOMAN) &&
                        person.getStatInFamily() == StatInFamily.PARENT && person.getAge() >= 20 && person.getAge() <= 40) {
                    parents.add(person);
                }else if(person.getType() == Types.BOY || person.getType() == Types.GIRL){
                    children.add(person);
                }
            });
            if (parents.size() == 2 && children.size() < 3) {
                family.add(f);
                break;
            }
        }
        return (!family.isEmpty()) ? family.get(0) : null;
    }

    public Housing buildHouse() {
        Housing housing;
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
                "\n         girls: " + Calculations.getPeopleCountByType(people, Types.GIRL) +
                "\n         boys: " + Calculations.getPeopleCountByType(people, Types.BOY) +
                "\n     }" +
                "\n     Woman: " + Calculations.getPeopleCountByType(people, Types.WOMAN) +
                "\n     Man: " + Calculations.getPeopleCountByType(people, Types.MAN) +
                "\n  }, " +
                "\n  families: " + families.size() +
                "\n }";
    }
}
