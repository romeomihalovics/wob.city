package wob.city.person.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.family.Family;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.city.City;
import wob.city.person.object.Girl;
import wob.city.food.object.Meat;
import wob.city.person.object.Woman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AgingWorkerTest {
    @Test
    @DisplayName("Aging worker should increase age by one and make child into adult if it reaches the age of 18 or kill it with a 10% chance [or with a 25% chance if over age 60]")
    void AgingWorkerShouldDo() {
        Person parent = new Woman();
        Person person = new Girl();
        person.setAge(17);
        List<Person> people = new ArrayList<>();
        people.add(person);
        people.add(parent);
        List<Person> syncedPeople = Collections.synchronizedList(people);

        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));

        City city = new City("Test City", syncedPeople, foods);
        city.getPeople().forEach(p -> p.setLocation(city));

        Family family = new Family(city, parent);
        family.tryToAdd(person, true);

        city.addFamily(family);

        person.setWorkers();

        AgingWorker agingWorker = new AgingWorker(person);
        agingWorker.run();

        assertEquals(18, person.getAge());
        assertTrue(people.size() == 2 || people.size() == 1);
        assertTrue(people.size() == 1 || people.get(0) instanceof Woman);
    }
}
