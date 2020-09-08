package wob.city.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.objects.Girl;
import wob.city.objects.Meat;
import wob.city.objects.Woman;

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
        Person person = new Girl();
        person.setAge(17);
        List<Person> people = new ArrayList<>();
        people.add(person);
        List<Person> syncedPeople = Collections.synchronizedList(people);

        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));

        City city = new City("Test City", syncedPeople, foods);
        city.getPeople().forEach(p -> p.setLocation(city));

        person.setWorkers();

        AgingWorker agingWorker = new AgingWorker(person);
        agingWorker.run();

        assertEquals(18, person.getAge());
        assertTrue(people.size() == 1 || people.size() == 0);
        assertTrue(people.size() == 0 || people.get(0) instanceof Woman);
    }
}
