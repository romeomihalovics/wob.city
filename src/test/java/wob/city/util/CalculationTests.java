package wob.city.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.food.abstraction.Food;
import wob.city.food.object.Meat;
import wob.city.person.abstraction.Person;
import wob.city.person.enums.Type;
import wob.city.person.object.Boy;
import wob.city.person.object.Girl;
import wob.city.person.object.Man;
import wob.city.person.object.Woman;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculationTests {
    @Test
    @DisplayName("Random int between 1 and 10 should be on list from 1 and 10")
    void getRandomIntBetweenShouldReturn() {
        int min = 1;
        int max = 10;
        List<Integer> shouldContain = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        assertTrue(shouldContain.contains(Calculation.getRandomIntBetween(min, max)));
    }

    @Test
    @DisplayName("People count by type should return 2 boy, 1 girl, 1 man, 2 woman")
    void getPeopleCountByTypeShouldReturn() {
        List<Person> people = Arrays.asList(new Boy(), new Boy(), new Girl(), new Man(), new Woman(), new Woman());

        assertEquals(2, Calculation.getPeopleCountByType(people, Type.BOY));
        assertEquals(1, Calculation.getPeopleCountByType(people, Type.GIRL));
        assertEquals(1, Calculation.getPeopleCountByType(people, Type.MAN));
        assertEquals(2, Calculation.getPeopleCountByType(people, Type.WOMAN));
    }

    @Test
    @DisplayName("Random 5 people from list should return random 10 people")
    void getRandomNPeopleShouldReturn() {
        List<Person> people = Arrays.asList(new Woman(), new Woman(), new Woman(), new Man(), new Man(), new Girl());

        assertEquals(5, Calculation.getRandomNPeople(people, 5).size());
    }

    @Test
    @DisplayName("100g Sausage with 14g protein, 1g carbohydrate, 28g fat should have 312 calories")
    void getEnergyShouldReturn() {
        List<String> foodDetails = Arrays.asList("Sausage", "14", "1", "28");
        Food food = new Meat(foodDetails);

        assertEquals(312, Calculation.getEnergy(food));
    }

    @Test
    @DisplayName("Calculate how much energy is available in x gramm food")
    void getEnergyByAmountShouldReturn() {
        int energyInAHundredGrammFood = 234;
        int desiredAmount = 512;

        assertEquals(1198.08, Calculation.getAmountByEnergy(desiredAmount, energyInAHundredGrammFood));
    }

    @Test
    @DisplayName("Rounding double '12.005' to 2 decimal places should return '12.01'")
    void roundShouldReturn() {
        assertEquals(12.01, Calculation.round(12.005, 2));
    }
}
