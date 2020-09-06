package wob.city.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.abstractions.Food;
import wob.city.abstractions.Person;
import wob.city.objects.City;
import wob.city.objects.Girl;
import wob.city.objects.Meat;
import wob.city.objects.Woman;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandUtilsTest {
    @Test
    @DisplayName("Parsing food name from console input with regex should return '(Food Object [Meat]) Sausage' when input is 'food -n Sausage' or with '-gramm 100', but fake name should return null")
    public void parseFoodNameShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(CommandUtils.parseFoodName(foods, "food -n Sausage", false), foods.get(0));
        assertEquals(CommandUtils.parseFoodName(foods, "food -n Sausage -gramm 100", true), foods.get(0));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake", false));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake -gramm 100", true));
    }

    @Test
    @DisplayName("Get food object by name should return '(Food [Meat]) Sausage' when searched by 'Sausage', fake name should return null")
    public void getFoodShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(CommandUtils.getFood(foods, "Sausage"), foods.get(0));
        assertNull(CommandUtils.getFood(foods, "Fake"));
    }

    @Test
    @DisplayName("Get food gramm from console input should return '100' when input is 'food -n name -gramm 100'")
    public void parseFoodGrammShouldReturn() {
        assertEquals(CommandUtils.parseFoodGramm("food -n name -gramm 100"), 100);
    }

    @Test
    @DisplayName("Parsing city name from console input with regex should return '(City Object)' when input is 'people -c City Name' or 'person -c City Name -n Person Name', but fake name should return null")
    public void parseCityNameShouldReturn() {
        List<City> cities = Arrays.asList(new City("WoB City", Arrays.asList(new Girl(), new Woman()), 2));

        assertEquals(CommandUtils.parseCityName(cities, "people -c WoB City", false), cities.get(0));
        assertEquals(CommandUtils.parseCityName(cities, "person -c WoB City -n Person Name", true), cities.get(0));
        assertNull(CommandUtils.parseCityName(cities, "people -c Fake Name", false));
        assertNull(CommandUtils.parseCityName(cities, "person -c Fake Name -n Person Name", true));
    }

    @Test
    @DisplayName("Parsing a specified person from a city with console input should return a '(Person Object)' if there is a match by the full name (first- + lastname), otherwise should return null")
    public void getPersonShouldReturn() {
        List<City> cities = Arrays.asList(new City("WoB City", Arrays.asList(new Girl(), new Woman()), 2));
        Person person = cities.get(0).getPeople().get(0);
        City city = CommandUtils.parseCityName(cities, "people -c WoB City", false);

        assertNotNull(city);

        assertEquals(CommandUtils.getPerson(city, "person -c WoB City -n " + person.getFullName()), person);
    }

    @Test
    @DisplayName("Get regex match should return the matcher object on successful matching or null on failed matching")
    public void getRegexMatchShouldReturn() {
        assertEquals(CommandUtils.getRegexMatch("test -n parameter", "^test -n (.*)$").group(1), "parameter");
        assertNull(CommandUtils.getRegexMatch("not matching", "^test -n (.*)$"));
    }
}
