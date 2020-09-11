package wob.city.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wob.city.disaster.abstraction.Disaster;
import wob.city.disaster.object.Volcano;
import wob.city.food.abstraction.Food;
import wob.city.person.abstraction.Person;
import wob.city.city.City;
import wob.city.person.object.Girl;
import wob.city.food.object.Meat;
import wob.city.person.object.Woman;

import java.util.*;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class CommandUtilsTest {
    @Test
    @DisplayName("Parsing food name from console input with regex should return '(Food Object [Meat]) Sausage' when input is 'food -n Sausage' or with '-gramm 100', but fake name should return null")
    void parseFoodNameShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(foods.get(0), CommandUtils.parseFoodName(foods, "food -n Sausage", false));
        assertEquals(foods.get(0), CommandUtils.parseFoodName(foods, "food -n Sausage -gramm 100", true));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake", false));
        assertNull(CommandUtils.parseFoodName(foods, "food -n Fake -gramm 100", true));
    }

    @Test
    @DisplayName("Get food object by name should return '(Food [Meat]) Sausage' when searched by 'Sausage', fake name should return null")
    void getFoodShouldReturn() {
        HashMap<String, List<String>> foodDetails = new HashMap<>();
        foodDetails.put("Sausage", Arrays.asList("Sausage", "14", "1", "28"));
        foodDetails.put("Chicken", Arrays.asList("Chicken", "30", "0", "3"));

        List<Food> foods = Arrays.asList(new Meat(foodDetails.get("Sausage")), new Meat(foodDetails.get("Chicken")));

        assertEquals(foods.get(0), CommandUtils.getFood(foods, "Sausage"));
        assertNull(CommandUtils.getFood(foods, "Fake"));
    }

    @Test
    @DisplayName("Get food gramm from console input should return '100' when input is 'food -n name -gramm 100'")
    void parseFoodGrammShouldReturn() {
        assertEquals(100, CommandUtils.parseFoodGramm("food -n name -gramm 100"));
    }

    @Test
    @DisplayName("Parsing city name from console input with regex should return '(City Object)' when input is 'people -c City Name' or 'person -c City Name -n Person Name', but fake name should return null")
    void parseCityNameShouldReturn() {
        List<Food> foods = new ArrayList<>();
        List<City> cities = Collections.singletonList(new City("WoB City", Arrays.asList(new Girl(), new Woman()), foods));

        assertEquals(cities.get(0), CommandUtils.parseCityName(cities, "people -c WoB City", "people", false));
        assertEquals(cities.get(0), CommandUtils.parseCityName(cities, "person -c WoB City -n Person Name", "person", true));
        assertEquals(cities.get(0), CommandUtils.parseCityName(cities, "disaster -c WoB City -n volcano", "disaster", true));

        assertNull(CommandUtils.parseCityName(cities, "people -c Fake Name", "people", false));
        assertNull(CommandUtils.parseCityName(cities, "person -c Fake Name -n Person Name", "person",true));
        assertNull(CommandUtils.parseCityName(cities, "disaster -c Fake Name -n volcano", "disaster", true));

        assertNull(CommandUtils.parseCityName(cities, "disaster -c WoB City -n volcano", "fakecommand", true));
        assertNull(CommandUtils.parseCityName(cities, "person -c WoB City -n Person Name", "fakecommand", true));
        assertNull(CommandUtils.parseCityName(cities, "people -c WoB City", "fakecommand", false));

    }

    @Test
    @DisplayName("Parsing a specified person from a city with console input should return a '(Person Object)' if there is a match by the full name (first- + lastname), otherwise should return null")
    void getPersonShouldReturn() {
        List<Food> foods = new ArrayList<>();
        List<City> cities = Collections.singletonList(new City("WoB City", Arrays.asList(new Girl(), new Woman()), foods));
        Person person = cities.get(0).getPeople().get(0);
        City city = CommandUtils.parseCityName(cities, "people -c WoB City", "people",false);

        assertNotNull(city);

        assertEquals(person, CommandUtils.getPerson(city, "person -c WoB City -n " + person.getFullName()));
    }

    @Test
    @DisplayName("Generating a disaster from console should return a valid disaster object")
    void getDisasterShouldReturn() {
        Disaster volcanic = CommandUtils.getDisaster("disaster -c WoB City -n volcano");
        assertTrue(volcanic instanceof Volcano);
        assertEquals("God", volcanic.getCause());
        assertEquals("Volcanic Eruption", volcanic.getName());
        assertEquals("volcano", volcanic.getId());
    }

    @Test
    @DisplayName("Get regex match should return the matcher object on successful matching or null on failed matching")
    void getRegexMatchShouldReturn() {
        Matcher matcher = CommandUtils.getRegexMatch("test -n parameter", "^test -n (.*)$");
        assertEquals("parameter", (matcher != null) ? matcher.group(1) : null);
        assertNull(CommandUtils.getRegexMatch("not matching", "^test -n (.*)$"));
    }
}
