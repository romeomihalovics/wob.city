package wob.city.objects;

import wob.city.abstractions.NewsPaper;
import wob.city.abstractions.Person;

import java.util.ArrayList;
import java.util.List;

public class DeathNews extends NewsPaper {
    public DeathNews() {
        super("DeathNews", new ArrayList<Person>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addData(Object data) {
        ((List<Person>) this.getData()).add((Person) data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void flushData() {
        ((List<Person>) this.getData()).removeAll((List<Person>) this.getData());
    }

    @Override
    public String toString() {
        return this.getData().toString();
    }
}
