package wob.city.newspaper.object;

import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.person.abstraction.Person;

import java.util.ArrayList;
import java.util.List;

public class DeathNews extends NewsPaper {
    private final List<Person> deathData;

    @SuppressWarnings("unchecked")
    public DeathNews() {
        super("DeathNews", new ArrayList<Person>(), true);
        this.deathData = (List<Person>) this.getData();
    }

    @Override
    public void addData(Object data) {
        deathData.add((Person) data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void flushData() {
        deathData.removeAll((List<Person>) this.getData());
    }

    @Override
    public String toString() {
        return deathData.toString();
    }
}
