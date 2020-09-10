package wob.city.city;

import wob.city.console.logger.ConsoleLogger;
import wob.city.disaster.abstraction.Consequence;
import wob.city.disaster.abstraction.Disaster;
import wob.city.food.abstraction.Food;
import wob.city.newspaper.object.ConsumptionNews;
import wob.city.newspaper.object.DeathNews;
import wob.city.newspaper.object.DisasterNews;
import wob.city.newspaper.object.NewBornNews;
import wob.city.person.abstraction.Person;
import wob.city.util.Calculations;
import wob.city.city.worker.NewBornWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class City {
    private String name;
    private List<Person> people;
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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addDied(Person person) {
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
        ConsoleLogger.getLogger().log("The disaster ("+disaster.getName()+") is ended in city '"+this.getName()+"' with "+this.disaster.getDied().size()+" deaths");
        this.disasterNews.addData(disaster);
        this.disasterNews.manualPublish();
        this.disaster.cancel();
        this.disaster = null;
    }

    public void continueDisaster(Disaster disaster) {
        if(disaster instanceof Consequence && this.disaster != null) {
            this.disasterNews.addData(this.disaster);
            this.disaster.cancel();
            ConsoleLogger.getLogger().log("A natural disaster ("+this.disaster.getName()+" with "+this.disaster.getDied().size()+" deaths) is being followed up with another disaster '"+disaster.getName()+"' in city '"+this.getName()+"'");
            this.disaster = disaster;
            this.disaster.setLocation(this);
            this.disaster.start();
        }
    }

    @Override
    public synchronized String toString() {
        return "\n City: {" +
                "\n name: '" + name + "'," +
                "\n population: " + people.size() + "," +
                "\n died: " + died.size() + "," +
                "\n people: {" +
                "\n     kids: {" +
                "\n         girls: " + Calculations.getPeopleCountByType(people, "Girl") +
                "\n         boys: " + Calculations.getPeopleCountByType(people, "Boy") +
                "\n     }" +
                "\n     Woman: " + Calculations.getPeopleCountByType(people, "Woman") +
                "\n     Man: " + Calculations.getPeopleCountByType(people, "Man") +
                "\n }";
    }
}
