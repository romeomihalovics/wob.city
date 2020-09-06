package wob.city.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculationsTests {
    @Test
    @DisplayName("Random int between 1 and 10 should be on list from 1 and 10")
    public void getRandomIntBetweenShouldReturn() {
        int min = 1;
        int max = 10;
        List<Integer> shouldContain = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        assertTrue(shouldContain.contains(Calculations.getRandomIntBetween(min, max)));
    }

    @Test
    @DisplayName("People count by type should return 2 boy, 1 girl, 1 man, 2 woman")
    public void getPeopleCountByTypeShouldReturn() {
        List<Person> people = Arrays.asList(new Boy(), new Boy(), new Girl(), new Man(), new Woman(), new Woman());

        assertEquals(Calculations.getPeopleCountByType(people, Boy.class), 2);
        assertEquals(Calculations.getPeopleCountByType(people, Girl.class), 1);
        assertEquals(Calculations.getPeopleCountByType(people, Man.class), 1);
        assertEquals(Calculations.getPeopleCountByType(people, Woman.class), 2);
    }

    @Test
    @DisplayName("Random 5 people from list should return random 10 people")
    public void getRandomNPeopleShouldReturn() {
        List<Person> people = Arrays.asList(new Woman(), new Woman(), new Woman(), new Man(), new Man(), new Girl());

        assertEquals(Calculations.getRandomNPeople(people, 5).size(), 5);
    }

    @Test
    @DisplayName("100g Sausage with 14g protein, 1g carbohydrate, 28g fat should have 312 calories")
    public void getEnergyShouldReturn() {
        List<String> foodDetails = Arrays.asList("Sausage", "14", "1", "28");
        Food food = new Meat(foodDetails);

        assertEquals(Calculations.getEnergy(food), 312);
    }

    @Test
    @DisplayName("Rounding double '12.005' to 2 decimal places should return '12.01'")
    public void roundShouldReturn() {
        assertEquals(Calculations.round(12.005, 2), 12.01);
    }
}
