package wob.city.objects;

import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.util.Calculations;
import wob.city.worker.NewBornWorker;

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


    public City(String name, List<Person> people, List<Food> foods) {
        this.name = name;
        this.people = people;
        this.foods = foods;
        this.consumptionNews = new ConsumptionNews();
        this.deathNews = new DeathNews();
        this.newBornNews = new NewBornNews();
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

    @Override
    public synchronized String toString() {
        return "\n City: {" +
                "\n name: '" + name + "'," +
                "\n population: " + people.size() + "," +
                "\n died: " + died.size() + "," +
                "\n people: {" +
                "\n     kids: {" +
                "\n         girls: " + Calculations.getPeopleCountByType(people, Girl.class) +
                "\n         boys: " + Calculations.getPeopleCountByType(people, Boy.class) +
                "\n     }" +
                "\n     Woman: " + Calculations.getPeopleCountByType(people, Woman.class) +
                "\n     Man: " + Calculations.getPeopleCountByType(people, Man.class) +
                "\n }";
    }
}
