package wob.city.newspaper.object;

import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.person.abstraction.Person;

import java.util.ArrayList;
import java.util.List;

public class NewBornNews extends NewsPaper {
    private final List<Person> newBornData;

    public NewBornNews(){
        super("NewBornNews", new ArrayList<Person>(), true);
        this.newBornData = (List<Person>) this.getData();
    }

    @Override
    public void addData(Object data) {
        newBornData.add((Person) data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void flushData() {
        newBornData.removeAll((List<Person>) this.getData());
    }

    @Override
    public String toString() {
        return newBornData.toString();
    }
}
