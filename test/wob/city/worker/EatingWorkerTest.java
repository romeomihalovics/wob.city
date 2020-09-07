package wob.city.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.objects.Meat;
import wob.city.objects.Woman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EatingWorkerTest {
    @Test
    @DisplayName("After eating person's energy level should increase to max 2500 cal or stay the same [in case it decides to eat nothing]")
    void afterEatingEnergyLevelShouldIncrease() {
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

        EatingWorker eatingWorker = new EatingWorker(person);

        eatingWorker.run();

        assertTrue(person.getEnergy() < 2500 && person.getEnergy() >= 2150);
    }
}
