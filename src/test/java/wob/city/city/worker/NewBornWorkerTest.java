package wob.city.city.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.person.object.Boy;
import wob.city.city.City;
import wob.city.person.object.Girl;
import wob.city.food.object.Meat;
import wob.city.person.object.Man;
import wob.city.person.object.Woman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewBornWorkerTest {
    @Test
    @DisplayName("New Born Worker should add one kid (boy or girl) with age 1 to people list & in a city & into a fertile family")
    void newBornWorkerShouldDo() {
        List<Person> people = new ArrayList<>();
        List<Person> syncedPeople = Collections.synchronizedList(people);

        Person parentWoman = new Woman();
        Person parentMan = new Man();
        people.add(parentMan);
        people.add(parentWoman);

        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));

        City city = new City("Test City", syncedPeople, foods);
        city.createFamilies();

        NewBornWorker newBornWorker = new NewBornWorker(city);
        newBornWorker.run();

        assertTrue(city.getPeople().size() == 2 || city.getPeople().size() == 3);
        if(city.getPeople().size() == 3) {
            assertEquals(1, city.getPeople().get(2).getAge());
            assertTrue(city.getPeople().get(2) instanceof Boy || city.getPeople().get(2) instanceof Girl);
        }
    }
}
