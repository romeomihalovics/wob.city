package wob.city.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.objects.Meat;
import wob.city.objects.Woman;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DigestionWorkerTest {
    @Test
    @DisplayName("Digestion worker should decrease the person's energy level [by default 2500] by 350 calories and if it reaches 0 it should be removed from city population")
    void digestionWorkerShouldDecreaseEnergy() {
        Person person = new Woman();
        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));
        List<Person> people = new ArrayList<>();
        people.add(person);
        List<Person> syncedPeople = Collections.synchronizedList(people);

        City city = new City("Test City", syncedPeople, 1, foods);
        city.getPeople().forEach(p -> p.setLocation(city));


        DigestionWorker digestionWorker = new DigestionWorker(person);

        digestionWorker.run();

        assertEquals(2500 - 350, person.getEnergy());

        person.setWorkers();
        person.die();

        assertEquals(0, people.size());
    }
}
