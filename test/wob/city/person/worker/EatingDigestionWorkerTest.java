package wob.city.person.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.city.City;
import wob.city.food.object.Meat;
import wob.city.person.object.Woman;
import wob.city.person.worker.DigestionWorker;
import wob.city.person.worker.EatingWorker;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EatingDigestionWorkerTest {
    @Test
    @DisplayName("Digestion worker should decrease the person's energy level [by default 2500] by 350 calories and if it reaches 0 it should be removed from city population whilst eating worker should increase a person energy levels to max 2500 cal or stay the same [in case it decides to eat nothing]")
    void digestionWorkerShouldDecreaseEatingWorkerShouldIncreaseEnergy() {
        Person person = new Woman();
        List<Person> people = new ArrayList<>();
        people.add(person);
        List<Person> syncedPeople = Collections.synchronizedList(people);

        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));

        City city = new City("Test City", syncedPeople, foods);
        city.getPeople().forEach(p -> p.setLocation(city));


        DigestionWorker digestionWorker = new DigestionWorker(person);
        digestionWorker.run();
        assertEquals(2500 - 350, person.getEnergy());

        EatingWorker eatingWorker = new EatingWorker(person);
        eatingWorker.run();
        assertTrue(person.getEnergy() < 2500 && person.getEnergy() >= 2150);

        person.setWorkers();
        person.die();
        assertEquals(0, people.size());
    }
}
