package wob.city.objects;

import wob.city.abstractions.Person;
import wob.city.util.Calculations;

import java.util.List;

public class City {
    private String name;
    private int population;
    private List<Person> people;

    public City(String name, List<Person> people, int population) {
        this.name = name;
        this.people = people;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "\n City: {" +
                "\n name: '" + name + "'," +
                "\n population: '" + population + "'," +
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
