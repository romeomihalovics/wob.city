package wob.city.worker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.Boy;
import wob.city.objects.City;
import wob.city.objects.Girl;
import wob.city.objects.Meat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewBornWorkerTest {
    @Test
    @DisplayName("New Born Worker should add one kid (boy or girl) with age 1 to people list in city")
    void newBornWorkerShouldDo() {
        List<Person> people = new ArrayList<>();
        List<Person> syncedPeople = Collections.synchronizedList(people);

        List<Food> foods = Collections.singletonList(new Meat(Arrays.asList("Sausage", "14", "1", "28")));

        City city = new City("Test City", syncedPeople, foods);

        NewBornWorker newBornWorker = new NewBornWorker(city);
        newBornWorker.run();

        assertEquals(1, city.getPeople().size());
        assertEquals(1, city.getPeople().get(0).getAge());
        assertTrue(city.getPeople().get(0) instanceof Boy || city.getPeople().get(0) instanceof Girl);
    }
}
